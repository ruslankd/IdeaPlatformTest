package ru.kabirov.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.kabirov.database.model.GoodsDbo

@Dao
interface GoodsDao {
    @Query("select * from item")
    fun getAllGoods(): Flow<List<GoodsDbo>>

    @Query("select * from item where lower(name) like lower('%' || :query || '%')")
    fun getGoodsByQuery(query: String): Flow<List<GoodsDbo>>

    @Query("delete from item where id = :id")
    suspend fun deleteGoodsById(id: Long)

    @Query("update item set amount = :amount where id = :id")
    suspend fun updateGoodsAmountById(id: Long, amount: Long)
}