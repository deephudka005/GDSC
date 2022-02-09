package com.example.gdsc_app.data.data_class
//For adding order to the firestore database
data class Data(var uid: String ="",
                var displayName:String?="",
                var email: String="",
                var product: String?= "",
                var quatity: String?= "",
                var shining: String?="",
                var date: String?="")