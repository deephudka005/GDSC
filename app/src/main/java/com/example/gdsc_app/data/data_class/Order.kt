package com.example.gdsc_app.data.data_class

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_table")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id:  Int = 0,
    @ColumnInfo(name = "name")
    val product_name: String,
    @ColumnInfo(name = "Quantity")
    val quantity: String,
    @ColumnInfo(name = "Shining")
    val shining: String,
    @ColumnInfo(name = "Order Date")
    val date: String
)

