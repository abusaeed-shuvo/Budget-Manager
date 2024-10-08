package com.example.budget_manager.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budget_manager.R
import com.example.budget_manager.data.transaction.Transaction
import com.example.budget_manager.data.transaction.TransactionDatabase
import com.example.budget_manager.databinding.FragmentTransactionsBinding
import com.example.budget_manager.ui.adapters.TransactionAdapter
import kotlinx.coroutines.launch

class TransactionsFragment : Fragment(), TransactionAdapter.TransactionClicker {
    private lateinit var binding: FragmentTransactionsBinding
    private lateinit var adapter: TransactionAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionsBinding.inflate(inflater, container, false)


        adapter = TransactionAdapter(this)


        binding.transactionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.transactionsRecyclerView.adapter = adapter


        lifecycleScope.launch {
            val transactionList =
                TransactionDatabase.getDB(requireContext()).getTransactionDao().getAllData()
            adapter.submitList(transactionList)
        }





        return binding.root
    }

    override fun onTransactionClick(transaction: Transaction) {
        val date = "${transaction.date}/${transaction.month + 1}/${transaction.year}"
        val msg = "  ${transaction.amount}\n" +
                "Date:$date"


        var bundle = Bundle()
        bundle.putInt("id", transaction.id)

        val builder = AlertDialog.Builder(requireContext(), R.style.TransactionAlertDialog)
        builder.setTitle("${transaction.title} ")


        if (transaction.transactionType == 0) {
            builder.setMessage("Spent:$msg")
        } else {
            builder.setMessage("Earned:$msg")
        }

        builder.setNegativeButton("close") { p0, _ ->
            p0.cancel()
        }
        builder.setPositiveButton("Edit") { _, _ ->
            if (transaction.transactionType == 0) {
                findNavController().navigate(
                    R.id.action_transactionsFragment_to_addExpenseFragment,
                    bundle
                )
            } else {
                findNavController().navigate(
                    R.id.action_transactionsFragment_to_addIncomeFragment,
                    bundle
                )
            }
        }
        builder.show()
    }
}
