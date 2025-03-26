package ru.kabirov.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import ru.kabirov.data.api.GoodsRepository
import ru.kabirov.data.mapper.toGoods
import ru.kabirov.data.model.Goods
import ru.kabirov.data.model.RequestResult
import ru.kabirov.database.GoodsDatabase
import javax.inject.Inject

class GoodsRepositoryImpl @Inject constructor(
    private val database: GoodsDatabase,
) : GoodsRepository {
    override fun getGoods(): Flow<RequestResult<List<Goods>>> {
        val start = flowOf<RequestResult<List<Goods>>>(RequestResult.InProgress())
        val goodsFlow = database.goodsDao.getAllGoods().map { goodsList ->
            RequestResult.Success(goodsList.map { it.toGoods() })
        }

        return merge(start, goodsFlow)
    }
}