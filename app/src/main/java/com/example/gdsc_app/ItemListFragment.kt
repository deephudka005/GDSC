package com.example.gdsc_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdsc_app.databinding.ItemListFragmentBinding
import com.example.gdsc_app.model.OrderViewModel
import com.example.gdsc_app.model.OrderViewModelFactory

class ItemListFragment : Fragment() {
    private var binding: ItemListFragmentBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels{
        OrderViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }

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

        val mAdapter = ItemListAdapter()
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this.context)
        binding?.recyclerView?.adapter = mAdapter


        sharedViewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                mAdapter.submitList(it)
            }
        }
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

    fun sendOrder(){
        val orderSummary = getString(
           R.string.order_details,
           sharedViewModel.epoxygrout.value.toString(),
           sharedViewModel.quantity.value.toString(),
           sharedViewModel.shining.value.toString(),
           sharedViewModel.date.value.toString(),
       )

        // Create an ACTION_SEND implicit intent with order details in the intent extras
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_epoxygrout_order))
            .putExtra(Intent.EXTRA_TEXT, orderSummary)

        // Check if there's an app that can handle this intent before launching it
        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            // Start a new activity with the given intent (this may open the share dialog on a
            // device if multiple apps can handle this intent)
            startActivity(intent)
        }
        findNavController().navigate(R.id.action_itemListFragment_to_epoxyFragment)
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
