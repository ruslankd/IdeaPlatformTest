package ru.kabirov.main.viewmodel

import ru.kabirov.data.model.Goods
import ru.kabirov.data.model.RequestResult

sealed class UiState {
    data object None : UiState()

    data object Loading : UiState()

    class Error(val error: Throwable) : UiState()

    class Success(val stateData: StateData) : UiState()
}

internal fun RequestResult<List<Goods>>.toUiState(): UiState {
    return when (this) {
        is RequestResult.Error -> UiState.Error(error)
        is RequestResult.InProgress -> UiState.Loading
        is RequestResult.Success -> UiState.Success(StateData(data))
    }
}