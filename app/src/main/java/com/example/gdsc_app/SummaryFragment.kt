package com.example.gdsc_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.gdsc_app.data.Order
import com.example.gdsc_app.databinding.FragmentSummaryBinding
import com.example.gdsc_app.model.OrderViewModel
//import com.example.gdsc_app.model.OrderViewModelFactory

class SummaryFragment: Fragment() {
    // Binding object instance corresponding to the fragment_summary.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentSummaryBinding? = null
    lateinit var item: Order
    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact

    private val sharedViewModel: OrderViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryFragment = this@SummaryFragment
        }
    }

    /**
     * Submit the order by sharing out the order details to another app via an implicit intent.
     */
    private fun addNewItem() {
        val epoxygrout = sharedViewModel.epoxygrout.value.toString()
        val quantity = sharedViewModel.quantity.value.toString()
        val shining = sharedViewModel.shining.value.toString()
        val date = sharedViewModel.date.value.toString()
        //create order object

        //add order
        sharedViewModel.addOrder(Order(0,epoxygrout,quantity,shining,date))
        Toast.makeText(this.context,"Successfully Added!",Toast.LENGTH_LONG).show()
    }
    fun addOrder(){
        addNewItem()
        findNavController().navigate(R.id.action_summaryFragment_to_itemListFragment)
    }
    /*fun sendOrder() {
        // Construct the order summary text with information from the view model
        //addNewItem()


    }*/


    fun cancelOrder() {
        // Reset order in view model
        sharedViewModel.resetOrder()

        // Navigate back to the [StartFragment] to start over
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}