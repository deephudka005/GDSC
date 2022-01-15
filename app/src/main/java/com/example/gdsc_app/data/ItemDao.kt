package com.example.gdsc_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {


    @Query("SELECT * from order_table ORDER BY name ASC")
    fun getItems(): Flow<List<Order>>

    @Query("SELECT * from order_table WHERE id = :id")
    fun getItem(id: Int): Flow<Order>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addOrder(order: Order)

    @Query("SELECT * from order_table ORDER BY id ASC")
    fun readAllData():LiveData<List<Order>>


    @Delete
    suspend fun delete(item: Order){}




}