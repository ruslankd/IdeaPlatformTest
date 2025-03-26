package ru.kabirov.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.kabirov.database.dao.GoodsDao
import ru.kabirov.database.model.GoodsDbo

class GoodsDatabase internal constructor(private val database: GoodsRoomDatabase) {
    val goodsDao: GoodsDao
        get() = database.goodsDao()
}

@Database(
    entities = [
        GoodsDbo::class,
    ],
    version = 1,
    exportSchema = true,
)
internal abstract class GoodsRoomDatabase : RoomDatabase() {
    abstract fun goodsDao(): GoodsDao
}

fun GoodsDatabase(applicationContext: Context): GoodsDatabase {
    return GoodsDatabase(
        Room.databaseBuilder(
            applicationContext,
            GoodsRoomDatabase::class.java,
            "data.db"
        )
            .createFromAsset("db/data.db")
            .build()
    )
}