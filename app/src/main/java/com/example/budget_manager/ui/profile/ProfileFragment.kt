package com.example.budget_manager.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.budget_manager.R
import com.example.budget_manager.data.user.DBNODES
import com.example.budget_manager.data.user.User
import com.example.budget_manager.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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
        var userName:String=firebaseUser.displayName.toString()
        var userEmail:String=firebaseUser.email.toString()

//        userDB.child(DBNODES.USER).child(firebaseUser.uid).addValueEventListener(
//            object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    snapshot.getValue(User::class.java)?.let {
//                        binding.apply {
//                            userNameTV.text = it.userName
//                            userEmailTV.text = it.email
//                        }
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    Toast.makeText(requireContext(), "${error.message} ", Toast.LENGTH_SHORT).show()
//                    findNavController().navigate(R.id.action_profileFragment_to_signInFragment)
//                }
//
//            }
//        )

        binding.apply {
            userNameTV.text = userName
            userEmailTV.text = userEmail
        }

        binding.btnLogout.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            auth.signOut()
            findNavController().navigate(R.id.action_profileFragment_to_signInFragment)
        }



        return binding.root
    }
}