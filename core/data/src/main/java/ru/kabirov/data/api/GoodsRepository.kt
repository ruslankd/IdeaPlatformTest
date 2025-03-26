package ru.kabirov.data.api

import kotlinx.coroutines.flow.Flow
import ru.kabirov.data.model.Goods
import ru.kabirov.data.model.RequestResult

interface GoodsRepository {
    fun getGoods(): Flow<RequestResult<List<Goods>>>
}