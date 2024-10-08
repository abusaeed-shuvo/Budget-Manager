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
import com.example.budget_manager.data.user.DBNODES
import com.example.budget_manager.data.user.User
import com.example.budget_manager.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var userDB: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        userDB = FirebaseDatabase.getInstance().reference

        binding.signInTV.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.userEmail.text.toString().trim()
            val password = binding.userPassword.text.toString().trim()
            val userName = binding.userName.text.toString().trim()
            val auth = FirebaseAuth.getInstance()


            if (isEmailValid(email) && isPasswordValid(password)) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val uid = auth.currentUser?.uid
                        auth.currentUser?.uid?.let {
                            val user = User(userId = uid, email = email, userName = userName)
                            userDB.child(DBNODES.USER).setValue(user).addOnCompleteListener { p0 ->
                                if (p0.isSuccessful) {
                                    findNavController().navigate(R.id.action_signUpFragment_to_profileFragment)
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "${p0.exception?.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(activity, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
            }


        }


        return binding.root
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        val passRegex = Regex("^(?=.*[A-Za-z])(?=.*[@$!%*#?&])[A-Za-z@$!%*#?&\\d]{6,}$")

        return password.matches(passRegex)
    }

}