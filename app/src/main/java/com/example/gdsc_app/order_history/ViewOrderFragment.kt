package com.example.gdsc_app.order_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdsc_app.databinding.FragmentVieworderBinding
import com.example.gdsc_app.model.OrderViewModel

//import com.example.gdsc_app.model.OrderViewModelFactory

class ViewOrderFragment: Fragment() {
    private var binding: FragmentVieworderBinding? = null
    lateinit var sharedViewModel: OrderViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentVieworderBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel= ViewModelProvider(this).get(OrderViewModel::class.java)
        val mAdapter = ViewOrderAdapter(this)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this.context)
        binding?.recyclerView?.adapter = mAdapter

        sharedViewModel.allOrder.observe(viewLifecycleOwner, Observer {list->
            list?.let {
                mAdapter.setData(it)
            }
        })
        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.
        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            viewModel = sharedViewModel

            // Assign the fragment
            vieworder = this@ViewOrderFragment
        }

    }
}