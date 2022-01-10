package com.example.gdsc_app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order(@PrimaryKey(autoGenerate = true)
                  val id:  Int = 0,
                  @ColumnInfo(name = "Name of Product")
                  val product_name: String,
                  @ColumnInfo(name = "Quantity")
                  val quantity: String,
                  @ColumnInfo(name = "Shining")
                  val shining: String,
                  @ColumnInfo(name = "Order Date")
                  val date: String
)