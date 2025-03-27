package ru.kabirov.main.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kabirov.main.R
import ru.kabirov.main.viewmodel.GoodsViewModel

@Composable
internal fun Searcher(viewModel: GoodsViewModel) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        value = viewModel.searchQuery,
        onValueChange = { viewModel.updateQuery(it) },
        label = {
            Text(text = stringResource(R.string.goods_select))
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "")
        },
        trailingIcon = {
            if (viewModel.searchQuery.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable { viewModel.updateQuery("") },
                    imageVector = Icons.Default.Clear,
                    contentDescription = null
                )
            }
        }
    )
}