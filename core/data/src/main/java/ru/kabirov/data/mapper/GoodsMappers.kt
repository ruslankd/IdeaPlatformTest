package ru.kabirov.data.mapper

import ru.kabirov.data.model.Goods
import ru.kabirov.database.model.GoodsDbo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal fun GoodsDbo.toGoods(): Goods =
    Goods(
        id = id,
        name = name,
        date = time.toDateString(),
        tags = tags.toTagsList(),
        amount = amount
    )

private fun String.toTagsList(): List<String> =
    if (length > 1) {
        substring(1..<length - 1)
            .split(", ")
            .map { if (it.length > 1) it.substring(1..<it.length - 1) else "" }
    } else {
        emptyList()
    }

private fun Long.toDateString(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return format.format(date)
}