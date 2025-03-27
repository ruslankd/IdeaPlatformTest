package ru.kabirov.main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.kabirov.data.api.GoodsRepository
import javax.inject.Inject

@HiltViewModel
class GoodsViewModel @Inject constructor(
    private val goodsRepository: GoodsRepository,
) : ViewModel() {
    var searchQuery by mutableStateOf("")
    var currentGoodsId by mutableLongStateOf(0L)
    var currentGoodsAmount by mutableLongStateOf(0L)

    @OptIn(ExperimentalCoroutinesApi::class)
    var state: StateFlow<UiState> =
        snapshotFlow { searchQuery }
            .flatMapLatest { query ->
                if (query.isEmpty()) {
                    goodsRepository.getGoods()
                } else {
                    goodsRepository.getGoodsByQuery(query)
                }
            }
            .map { it.toUiState() }
            .flowOn(Dispatchers.IO)
            .stateIn(viewModelScope, SharingStarted.Lazily, UiState.None)

    fun updateQuery(input: String) {
        searchQuery = input
    }

    fun updateGoods(count: Long) {
        viewModelScope.launch {
            goodsRepository.updateGoodsAmountById(
                id = currentGoodsId,
                amount = count
            )
        }
    }

    fun deleteGoods() {
        viewModelScope.launch {
            goodsRepository.deleteGoods(currentGoodsId)
        }
    }
}