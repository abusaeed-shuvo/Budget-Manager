package com.example.budget_manager.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.budget_manager.R
import com.example.budget_manager.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProfileFragment : Fragment() {

	private lateinit var binding: FragmentProfileBinding
	private lateinit var userDB: DatabaseReference
	private lateinit var firebaseUser: FirebaseUser


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentProfileBinding.inflate(inflater, container, false)
		userDB = FirebaseDatabase.getInstance().reference
		FirebaseAuth.getInstance().currentUser?.let {
			firebaseUser = it
		}



		binding.userEmailTV.text = firebaseUser.email



		binding.btnLogout.setOnClickListener {
			val auth = FirebaseAuth.getInstance()
			auth.signOut()
			findNavController().navigate(R.id.action_profileFragment_to_signInFragment)
		}



		return binding.root
	}

}