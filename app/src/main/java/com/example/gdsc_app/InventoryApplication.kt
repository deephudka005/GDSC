package com.example.gdsc_app

import android.app.Application
import com.example.gdsc_app.data.ItemRoomDatabase

class InventoryApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}