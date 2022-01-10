package com.example.gdsc_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.gdsc_app.databinding.FragmentSummaryBinding
import com.example.gdsc_app.model.OrderViewModel

class SummaryFragment: Fragment() {
    // Binding object instance corresponding to the fragment_summary.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentSummaryBinding? = null

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact

    private val sharedViewModel: OrderViewModel by activityViewModels()
    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.applicationContext as InventoryApplication).database
                .itemDao()
        )
    }
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
            viewModel.addNewItem(
                sharedViewModel.epoxygrout.value.toString(),
                sharedViewModel.quantity.value.toString(),
                sharedViewModel.shining.value.toString(),
                sharedViewModel.date.value.toString(),
            )
    }
    fun sendOrder() {
        // Construct the order summary text with information from the view model
        //addNewItem()

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
    }

    /**
     * Cancel the order and start over.
     */
    fun cancelOrder() {
        // Reset order in view model
        sharedViewModel.resetOrder()

        // Navigate back to the [StartFragment] to start over
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        binding = null
    }
}