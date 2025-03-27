package ru.kabirov.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kabirov.data.model.Goods
import ru.kabirov.main.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GoodsItem(
    goods: Goods,
    onDeleteClick: (Long) -> Unit,
    onUpdateClick: (Long, Long) -> Unit,
) {
    val maxWidthModifier = Modifier.fillMaxWidth()
    Box(
        modifier = maxWidthModifier
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = maxWidthModifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(modifier = maxWidthModifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = goods.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Icon(
                    modifier = Modifier.clickable { onUpdateClick(goods.id, goods.amount) },
                    imageVector = Icons.Default.Create,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Icon(
                    modifier = Modifier.clickable { onDeleteClick(goods.id) },
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
            FlowRow(
                modifier = maxWidthModifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                goods.tags.forEach {
                    if (it.isNotEmpty()) {
                        SuggestionChip(
                            onClick = {},
                            label = { Text(text = it, fontSize = 13.sp) },
                        )
                    }
                }
            }
            Row(modifier = maxWidthModifier) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.in_stock),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = if (goods.amount > 1) goods.amount.toString() else stringResource(R.string.not_available),
                        fontSize = 13.sp
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.date_added),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = goods.date, fontSize = 13.sp)
                }
            }
        }
    }
}