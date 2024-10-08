package com.example.budget_manager.data.transaction

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
    suspend fun getAllData(): List<Transaction>

    @Query("SELECT * FROM `Transaction` WHERE id IN (:id)")
    fun getDataWithId(id:List<Int>):List<Transaction>

@Query("SELECT * FROM `Transaction` WHERE month IN (:month) AND year IN (:year)")
    fun getAllDataWithMonthAndYear(month:Int,year:Int):List<Transaction>

}