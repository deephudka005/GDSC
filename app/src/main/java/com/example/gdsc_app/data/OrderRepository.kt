package com.example.gdsc_app.data


import androidx.lifecycle.LiveData
import com.example.gdsc_app.data.Daos.ItemDao
import com.example.gdsc_app.data.data_class.Order

class OrderRepository(private val itemDao: ItemDao) {
    val AllData: LiveData<List<Order>> = itemDao.readAllData()

    suspend fun addOrder(order: Order){
        itemDao.addOrder(order)
    }
    suspend fun delete(order: Order){
        itemDao.delete(order)
    }
}