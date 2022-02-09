package com.example.gdsc_app.data.Daos

import com.example.gdsc_app.data.data_class.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
//This Dao contains the user details
class UserDao {
    private val db = FirebaseFirestore.getInstance()
    private val userscollection =db.collection("users")

    fun addUser(user: User?){
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                userscollection.document(user.uid).set(it)
            }
        }
    }
}