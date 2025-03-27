package ru.kabirov.main.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.kabirov.main.viewmodel.GoodsViewModel
import ru.kabirov.main.viewmodel.UiState

@Composable
fun GoodsScreen(
    viewModel: GoodsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    GoodsContent(
        viewModel = viewModel,
        modifier = modifier,
    )
}

@Composable
fun GoodsContent(
    viewModel: GoodsViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var openDeleteDialog by remember { mutableStateOf(false) }
    var openUpdateDialog by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Title()

        Searcher(viewModel)

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (state) {
                is UiState.None -> Unit
                is UiState.Success ->
                    GoodsList(
                        state = state as UiState.Success,
                        onUpdateClick = { id, amount ->
                            openUpdateDialog = true
                            viewModel.currentGoodsId = id
                            viewModel.currentGoodsAmount = amount
                        },
                        onDeleteClick = {
                            openDeleteDialog = true
                            viewModel.currentGoodsId = it
                        },
                    )

                is UiState.Error -> Unit
                is UiState.Loading -> ProgressIndicator()
            }
        }
    }

    if (openUpdateDialog) {
        UpdateDialog(
            viewModel.currentGoodsAmount,
            onConfirm = {
                viewModel.updateGoods(it)
                openUpdateDialog = false
            },
            onDismiss = { openUpdateDialog = false }
        )
    }

    if (openDeleteDialog) {
        DeleteDialog(
            onConfirm = {
                viewModel.deleteGoods()
                openDeleteDialog = false
            },
            onDismiss = { openDeleteDialog = false }
        )
    }
}

@Composable
fun GoodsList(
    state: UiState.Success,
    onDeleteClick: (Long) -> Unit,
    onUpdateClick: (Long, Long) -> Unit,
) {
    val goodsList = state.stateData.goodsList

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(items = goodsList, key = { _, goods ->
            goods.id
        }) { index, goods ->
            GoodsItem(goods, onDeleteClick, onUpdateClick)
        }
    }
}