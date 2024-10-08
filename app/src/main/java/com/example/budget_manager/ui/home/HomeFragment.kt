package com.example.budget_manager.ui.home

import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.budget_manager.R
import com.example.budget_manager.data.transaction.TransactionDatabase
import com.example.budget_manager.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        var incomeTotal = 0.0
        var expenseTotal = 0.0


        val transactionList =
            TransactionDatabase.getDB(requireContext()).getTransactionDao()
                .getAllDataWithMonthAndYear(month, year)

        transactionList.forEach {
            if (it.transactionType == 0) {
                expenseTotal += it.amount
            } else {
                incomeTotal += it.amount
            }
        }

        binding.incomeTV.text = incomeTotal.toString()
        binding.expenseTV.text = expenseTotal.toString()

        if (incomeTotal > expenseTotal) {
            val savings = incomeTotal - expenseTotal
            binding.apply {


                monthlyReport.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.credit
                    )
                )
                currentStatus.text = "Your Current saving is $savings"

            }


        } else if (incomeTotal < expenseTotal) {
            val debt = expenseTotal - incomeTotal
            binding.apply {
                monthlyReport.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.debt
                    )
                )
                currentStatus.text = "Your current Debt is $debt"
            }


        } else if (incomeTotal == expenseTotal) {
            binding.apply {


                monthlyReport.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.credit
                    )
                )
                currentStatus.text = "You don't have any savings or debt"

            }
        } else {
            Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show()
        }


        binding.btnAdd.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), binding.btnAdd)
            popupMenu.menuInflater.inflate(R.menu.add_entry_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {

                when (it.itemId) {
                    R.id.income -> findNavController().navigate(R.id.action_homeFragment_to_addIncomeFragment)
                    R.id.expense -> findNavController().navigate(R.id.action_homeFragment_to_addExpenseFragment)
                }

                true
            }
            popupMenu.show()
        }

        return binding.root
    }
}