package ru.kabirov.data.model

data class Goods(
    val id: Long,
    val name: String,
    val date: String,
    val tags: List<String>,
    val amount: Long,
)
