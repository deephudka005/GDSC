package com.example.gdsc_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ItemDao {


    @Query("SELECT * from Order ORDER BY name ASC")
    fun getItems(): Flow<List<Order>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Order){}

    @Update
    suspend fun update(item: Order){}

    @Delete
    suspend fun delete(item: Order){}




}