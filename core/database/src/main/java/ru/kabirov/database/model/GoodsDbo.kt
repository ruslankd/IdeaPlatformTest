package ru.kabirov.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class GoodsDbo(
    @PrimaryKey
    val id: Long,
    val name: String,
    val time: Long,
    val tags: String,
    val amount: Long,
)
