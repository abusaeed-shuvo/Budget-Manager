package com.example.budget_manager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Transaction::class], version = 1)
abstract class TransactionDatabase : RoomDatabase() {
    abstract fun getTransactionDao(): TransactionDao


    companion object {
        private var database: TransactionDatabase? = null

        fun getDB(context: Context): TransactionDatabase {
            if (database == null) {
                database =
                    Room.databaseBuilder(context, TransactionDatabase::class.java, "Transaction-DB")
                        .allowMainThreadQueries().build()
                return database as TransactionDatabase
            } else {
                return database as TransactionDatabase
            }
        }
    }

}