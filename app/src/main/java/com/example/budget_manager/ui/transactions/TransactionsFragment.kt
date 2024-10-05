package com.example.budget_manager.ui.transactions

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.budget_manager.R
import com.example.budget_manager.databinding.FragmentProfileBinding
import com.example.budget_manager.databinding.FragmentTransactionsBinding

class TransactionsFragment : Fragment() {
    private lateinit var binding:FragmentTransactionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentTransactionsBinding.inflate(inflater,container,false)
        val root=binding.root


        return root
    }
}