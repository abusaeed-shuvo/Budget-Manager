package com.example.budget_manager.ui.entry.expense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.budget_manager.R
import com.example.budget_manager.databinding.FragmentAddExpenseBinding
import com.example.budget_manager.databinding.FragmentAddIncomeBinding

class AddExpenseFragment : Fragment() {
private lateinit var binding: FragmentAddExpenseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding= FragmentAddExpenseBinding.inflate(inflater,container,false)
        val root=binding.root


        return root


    }

}