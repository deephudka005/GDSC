package com.example.gdsc_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdsc_app.databinding.FragmentVieworderBinding
import kotlinx.android.synthetic.main.fragment_vieworder.*

class ViewOrderFragment: Fragment() {
    private var binding: FragmentVieworderBinding? = null
    lateinit var mAdapter: ItemListAdapter
    /*private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }*/
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
        mAdapter = ItemListAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }
        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.

    }
}