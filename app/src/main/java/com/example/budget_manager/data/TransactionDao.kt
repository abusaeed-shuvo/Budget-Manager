package com.example.budget_manager.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TransactionDao {
    @Insert
    fun insertData(transaction: Transaction)

    @Update
    fun updateData(transaction: Transaction)

    @Delete
    fun deleteData(transaction: Transaction)

    @Query("SELECT * FROM `Transaction`")
    fun getAllData(): List<Transaction>


}