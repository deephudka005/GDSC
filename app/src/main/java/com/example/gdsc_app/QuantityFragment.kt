package com.example.gdsc_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.gdsc_app.databinding.FragmentQuantityBinding
import com.example.gdsc_app.model.OrderViewModel

class QuantityFragment: Fragment() {
    private var binding: FragmentQuantityBinding? = null

    private val sharedViewModel: OrderViewModel by activityViewModels()
    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentQuantityBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            viewModel = sharedViewModel

            // Assign the fragment
            quantityFragment = this@QuantityFragment
        }
    }
    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding?.quantity?.text.toString()
        )
    }
    fun goToNextScreen() {
        if(isEntryValid()) {
            val value = binding?.quantity?.text.toString()
            sharedViewModel.setquantity(value)
        }
        findNavController().navigate(R.id.action_quantityFragment_to_shiningFragment)
    }
    fun cancelOrder() {
        // Reset order in view model
        sharedViewModel.resetOrder()

        // Navigate back to the [StartFragment] to start over
        findNavController().navigate(R.id.action_quantityFragment_to_startFragment)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}