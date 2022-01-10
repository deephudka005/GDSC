package com.example.gdsc_app

import androidx.lifecycle.*
import com.example.gdsc_app.data.Order
import com.example.gdsc_app.data.ItemDao
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the Inventory repository and an up-to-date list of all items.
 *
 */
class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {

    /**
     * Inserts the new Item into database.
     */
    val allItems: LiveData<List<Order>> = itemDao.getItems().asLiveData()

    fun addNewItem(product_name: String, quantity: String, shining: String, date: String) {
        val newItem = getNewItemEntry(product_name, quantity, shining,date)
        insertItem(newItem)
    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertItem(item: Order) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }
    fun deleteItem(item: Order) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }
    /**
     * Returns true if the EditTexts are not empty
     */
    fun isEntryValid(itemName: String): Boolean {
        if (itemName.isBlank()) {
            return false
        }
        return true
    }

    /**
     * Returns an instance of the [Item] entity class with the item info entered by the user.
     * This will be used to add a new entry to the Inventory database.
     */
    private fun getNewItemEntry(
        product: String, quantityoforder: String, shining: String, date: String
    ): Order {
        return Order(
            product_name = product,
            quantity =quantityoforder ,
            shining =shining,
            date = date
        )
    }

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}