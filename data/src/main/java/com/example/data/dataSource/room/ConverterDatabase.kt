package com.example.data.dataSource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.dataSource.room.account.dao.AccountDao
import com.example.data.dataSource.room.account.dbo.AccountDbo
import com.example.data.dataSource.room.converter.Converters
import com.example.data.dataSource.room.transaction.dao.TransactionDao
import com.example.data.dataSource.room.transaction.dbo.TransactionDbo
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [AccountDbo::class, TransactionDbo::class], version = 1)
@TypeConverters(Converters::class)
abstract class ConverterDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: ConverterDatabase? = null


        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context) : ConverterDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ConverterDatabase::class.java,
                        "habits_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}