package com.example.budget_manager.ui.entry

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.budget_manager.R
import com.example.budget_manager.data.transaction.Transaction
import com.example.budget_manager.data.transaction.TransactionDatabase
import com.example.budget_manager.databinding.FragmentAddExpenseBinding

class AddExpenseFragment : Fragment() {
    private lateinit var binding: FragmentAddExpenseBinding
    private lateinit var transaction: Transaction

    private var transactionType: Int = 0//expense
    private var transactionId = 0
    private var dayMain: Int = 0
    private var monthMain: Int = 0
    private var yearMain: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddExpenseBinding.inflate(inflater, container, false)
        val root = binding.root
        transactionId = arguments?.getInt("id", 0) ?: 0


        val calendar = Calendar.getInstance()
        dayMain = calendar.get(Calendar.DAY_OF_MONTH)
        monthMain = calendar.get(Calendar.MONTH)
        yearMain = calendar.get(Calendar.YEAR)


        if (transactionId != 0) {
            transaction =
                TransactionDatabase.getDB(requireContext()).getTransactionDao().getDataWithId(
                    listOf<Int>(transactionId)
                )[0]
            binding.apply {
                transactionExpenseTitleEdit.setText(transaction.title)
                transactionExpenseAmountEdit.setText(transaction.amount.toString())
                val showDate = "${transaction.date}/${transaction.month + 1}/${transaction.year}"
                binding.btnExpenseDate.text = showDate
            }
        }



        binding.btnExpenseDate.setOnClickListener {
            pickDate()
        }


        binding.btnSubmit.setOnClickListener {
            val titleStr = binding.transactionExpenseTitleEdit.text.toString()
            val amountInt = binding.transactionExpenseAmountEdit.text.toString().trim().toDouble()

            if (titleStr.isNotEmpty()) {
                var transaction = Transaction(
                    title = titleStr,
                    amount = amountInt,
                    transactionType = transactionType,
                    date = dayMain,
                    month = monthMain,
                    year = yearMain
                )

                if (transactionId == 0) {
                    TransactionDatabase.getDB(requireContext()).getTransactionDao()
                        .insertData(transaction)
                } else {
                    transaction.id = transactionId
                    TransactionDatabase.getDB(requireContext()).getTransactionDao()
                        .updateData(transaction)
                }
                findNavController().navigate(R.id.action_addExpenseFragment_to_transactionsFragment)

            } else {
                Toast.makeText(
                    requireActivity(),
                    "Blank transaction not allowed!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigate(R.id.action_addExpenseFragment_to_transactionsFragment)
        }



        return root


    }

    private fun pickDate() {

        val datePicker = DatePickerDialog(
            requireActivity(), { _, year, month, day ->
                yearMain = year
                monthMain = month
                dayMain = day


                val showDate = "$day/${month + 1}/$year"
                binding.btnExpenseDate.text = showDate

            }, yearMain, monthMain, dayMain
        )
        datePicker.show()
    }

}