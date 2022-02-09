package com.example.gdsc_app.data.Daos

import com.example.gdsc_app.data.data_class.Data
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
//This dao adds the final order to the firebase firestore database for company reference
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