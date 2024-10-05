package com.example.budget_manager.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Transaction(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,



    val title: String,
    val amount:Double,
    val transactionType:Int, //0=expense,1=income
    val date:Int,
    val month:Int,
    val year:Int
)
