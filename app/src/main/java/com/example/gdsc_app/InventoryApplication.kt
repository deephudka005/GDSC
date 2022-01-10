package com.example.gdsc_app

import android.app.Application
import com.example.gdsc_app.data.ItemRoomDatabase

class InventoryApplication : Application() {

    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}