package com.example.gdsc_app.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

/** Price for a single cupcake */
private const val PRICE_PER_CUPCAKE = 2.00

/** Additional cost for same day pickup of an order */
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00


class OrderViewModel : ViewModel() {

        // Quantity of cupcakes in this order
        private val _quantity = MutableLiveData<String>()
        val quantity: LiveData<String> = _quantity

        // Cupcake shining for this order
        private val _shining = MutableLiveData<String>()
        val shining: LiveData<String> = _shining

        /*private val _grout = MutableLiveData<String>()
        val grout: LiveData<String> = _grout*/

        // current date
        private val _date = MutableLiveData<String>()
        val date: LiveData<String> = _date

        // Price of the order so far
        private val _EpoxyGrout= MutableLiveData<String>()
        val epoxygrout: LiveData<String> = _EpoxyGrout

        init {
            // Set initial values for the order
            resetOrder()
        }
        fun setepoxy(product: String){
            _EpoxyGrout.value= product
        }
        fun setShining(desiredShining: String) {
            _shining.value = desiredShining
        }
        fun setquantity(quantity: String){
            _quantity.value = quantity
        }

         fun setDate() {
             val value= getDate()
            _date.value = value
         }
        fun resetOrder() {
            _quantity.value = "0"
            _shining.value = ""
            _date.value = date.toString()
        }



        private fun getDate(): String {
            val date = Calendar.getInstance().time
            val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()) //or use getDateInstance()
            val formatedDate = formatter.format(date)
            return formatedDate
        }

}