package com.example.gdsc_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdsc_app.databinding.FragmentVieworderBinding
import com.example.gdsc_app.model.OrderViewModel
import com.example.gdsc_app.model.OrderViewModelFactory

class ViewOrderFragment: Fragment() {
    private var binding: FragmentVieworderBinding? = null
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
        val fragmentBinding = FragmentVieworderBinding.inflate(inflater, container, false)
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
        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.

    }
}