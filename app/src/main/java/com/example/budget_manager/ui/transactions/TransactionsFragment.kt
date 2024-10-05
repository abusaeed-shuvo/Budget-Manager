package com.example.budget_manager.ui.transactions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.budget_manager.data.TransactionDatabase
import com.example.budget_manager.databinding.FragmentTransactionsBinding

class TransactionsFragment : Fragment(){
    private lateinit var binding:FragmentTransactionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionsBinding.inflate(inflater, container, false)

        var transaction= TransactionDatabase.getDB(requireContext()).getTransactionDao().getAllData()



        if (transaction.isEmpty()){
            val adapter=TransactionAdapter

        }







        return binding.root
    }
}
