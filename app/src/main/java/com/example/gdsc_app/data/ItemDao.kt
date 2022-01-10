package com.example.gdsc_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {


    @Query("SELECT * from `Order`")
    abstract fun getItems(): Flow<List<Order>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Order){}


    @Delete
    suspend fun delete(item: Order){}




}