package com.example.gdsc_app


import androidx.lifecycle.LiveData
import com.example.gdsc_app.data.ItemDao
import com.example.gdsc_app.data.Order

class OrderRepository(private val itemDao: ItemDao) {
    val AllData: LiveData<List<Order>> = itemDao.readAllData()

    suspend fun addOrder(order: Order){
        itemDao.addOrder(order)
    }
    suspend fun delete(order: Order){
        itemDao.delete(order)
    }
}