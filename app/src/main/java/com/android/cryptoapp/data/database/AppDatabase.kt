package com.android.cryptoapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.cryptoapp.data.network.model.CoinInfoDto

@Database(entities = [CoinInfoDbModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var db: AppDatabase? = null
        private const val NAME = "app.db"
        val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    NAME
                ).fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao
}