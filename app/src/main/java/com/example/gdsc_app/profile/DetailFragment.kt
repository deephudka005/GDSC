package com.example.gdsc_app.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.gdsc_app.R
import com.example.gdsc_app.databinding.FragmentDetailBinding
import com.example.gdsc_app.model.OrderViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private var binding: FragmentDetailBinding? = null
    private lateinit var auth: FirebaseAuth
    private val sharedViewModel: OrderViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentDetailBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth= Firebase.auth
        val user=auth.currentUser
        val photourl= auth.getCurrentUser()?.getPhotoUrl()
        Picasso.get().load(photourl).into(binding?.profileImage)
        binding?.username?.setText(user?.displayName.toString())
        binding?.email?.setText(user?.email.toString())

        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            viewModel = sharedViewModel

            // Assign the fragment
            detailFragment = this@DetailFragment
        }
    }

    fun HomeScreen(){
        findNavController().navigate(R.id.action_detailFragment_to_startFragment)
    }
}