package com.example.gdsc_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.gdsc_app.databinding.FragmentEpoxyBinding
import com.example.gdsc_app.model.OrderViewModel
//import com.example.gdsc_app.model.OrderViewModelFactory

class EpoxyFragment: Fragment() {
    private var binding: FragmentEpoxyBinding? = null

    private val sharedViewModel: OrderViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentEpoxyBinding.inflate(inflater, container, false)
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
            epoxyFragment = this@EpoxyFragment
        }
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_epoxyFragment_to_quantityFragment)
    }
    fun cancelOrder() {
        // Reset order in view model
        sharedViewModel.resetOrder()

        // Navigate back to the [StartFragment] to start over
        findNavController().navigate(R.id.action_epoxyFragment_to_startFragment)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}