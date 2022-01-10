package com.example.gdsc_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gdsc_app.databinding.FragmentStartBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class StartFragment:Fragment() {
    private var binding: FragmentStartBinding? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth= Firebase.auth
        val user=auth.currentUser
        binding?.username?.setText(user?.displayName.toString())
        binding?.apply {
            // Set up the button click listeners
            placeAnOrder.setOnClickListener { place_order() }
            viewOrders.setOnClickListener { view_order() }
        }
    }

    private fun view_order() {

    }

    private fun place_order() {
        findNavController().navigate(R.id.action_startFragment_to_epoxyFragment)
    }
}