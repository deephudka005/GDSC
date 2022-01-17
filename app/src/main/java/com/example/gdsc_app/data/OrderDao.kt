package com.example.gdsc_app.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OrderDao {
    private val db = FirebaseFirestore.getInstance()
    private val orderscollection =db.collection("data")

    fun addOrder(order: Data?){
        val id = db.collection("data").document().id

        order?.let {
            GlobalScope.launch(Dispatchers.IO) {
                orderscollection.document(id).set(it)
            }
        }
    }
}