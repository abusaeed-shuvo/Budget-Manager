package com.example.budget_manager.ui.profile

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.budget_manager.R
import com.example.budget_manager.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignInBinding.inflate(inflater, container, false)
        FirebaseAuth.getInstance().currentUser?.let {
            firebaseUser = it
            findNavController().navigate(R.id.action_signInFragment_to_profileFragment)
        }


        binding.signUpTV.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.btnSignIn.setOnClickListener {
            val email = binding.userEmail.text.toString().trim()
            val password = binding.userPassword.text.toString().trim()
            if (isEmailValid(email)) {
                val auth = FirebaseAuth.getInstance()
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "Logged in successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_signInFragment_to_profileFragment)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } else {
                Toast.makeText(requireContext(), "Invalid Email or Password", Toast.LENGTH_SHORT)
                    .show()
            }
        }


        return binding.root
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}