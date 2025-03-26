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

    @Delete
    fun deleteGoods(goods: GoodsDbo)
}