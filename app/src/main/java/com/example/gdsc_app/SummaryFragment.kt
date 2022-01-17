package com.example.gdsc_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.gdsc_app.data.Data
import com.example.gdsc_app.data.Order
import com.example.gdsc_app.data.OrderDao
import com.example.gdsc_app.databinding.FragmentSummaryBinding
import com.example.gdsc_app.model.OrderViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//import com.example.gdsc_app.model.OrderViewModelFactory

class SummaryFragment: Fragment() {
    private lateinit var auth: FirebaseAuth
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
        auth= Firebase.auth
        val user=auth.currentUser
        val order= Data()
        order.uid = user?.uid.toString()
        order.displayName= user?.displayName.toString()
        order.email= user?.email.toString()
        order.product = epoxygrout
        order.quatity = quantity
        order.shining = shining
        order.date = date
        val orderdao = OrderDao()
        orderdao.addOrder(order)



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