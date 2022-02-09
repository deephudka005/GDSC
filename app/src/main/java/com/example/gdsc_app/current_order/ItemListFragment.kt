package com.example.gdsc_app.current_order

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdsc_app.R
import com.example.gdsc_app.databinding.ItemListFragmentBinding
import com.example.gdsc_app.model.OrderViewModel
import kotlinx.android.synthetic.main.item_list_fragment.*


//import com.example.gdsc_app.model.OrderViewModelFactory

class ItemListFragment : Fragment() {
    private var binding: ItemListFragmentBinding? = null
    lateinit var sharedViewModel: OrderViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = ItemListFragmentBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel= ViewModelProvider(this).get(OrderViewModel::class.java)
        val Adapter = ItemListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter=Adapter
        //binding?.recyclerView?.adapter = mAdapter


        sharedViewModel.allOrder.observe(viewLifecycleOwner, Observer {list->
            list?.let {
                Adapter.setData(it)
            }
        })
        binding?.floatingActionButton?.setOnClickListener {
            startOrder()
        }
        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            viewModel = sharedViewModel

            // Assign the fragment
            itemlist = this@ItemListFragment
        }
    }
    fun clearOrder(){

    }
    fun sendOrder(){
        val orderlist = sharedViewModel.allOrder.value.toString()
        val intent = Intent(Intent.ACTION_SEND_MULTIPLE)
            .putExtra(Intent.EXTRA_TEXT, orderlist)
            .setType("text/plain")


        // Check if there's an app that can handle this intent before launching it
        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            // Start a new activity with the given intent (this may open the share dialog on a
            // device if multiple apps can handle this intent)
         startActivity(intent)
        }
        findNavController().navigate(R.id.action_itemListFragment_to_startFragment)
    }
    fun startOrder(){

        findNavController().navigate(R.id.action_itemListFragment_to_epoxyFragment)
    }
    fun cancelOrder(){
        sharedViewModel.resetOrder()
        // Navigate back to the [StartFragment] to start over
        findNavController().navigate(R.id.action_itemListFragment_to_startFragment)
    }
}
