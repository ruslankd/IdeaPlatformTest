package ru.kabirov.main.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kabirov.main.R

@Composable
fun UpdateDialog(
    amount: Long,
    onConfirm: (Long) -> Unit,
    onDismiss: () -> Unit,
) {
    var count by remember { mutableLongStateOf(amount) }
    AlertDialog(
        icon = {
            Icon(imageVector = Icons.Default.Settings, contentDescription = null)
        },
        title = {
            Text(text = stringResource(R.string.goods_amount))
        },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { if (count > 0) count -= 1 },
                    painter = painterResource(R.drawable.reduce),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
                Text(text = count.toString(), fontSize = 20.sp, textAlign = TextAlign.Center)
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { count += 1 },
                    painter = painterResource(R.drawable.add),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(count)
                }
            ) {
                Text(stringResource(R.string.accept))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}