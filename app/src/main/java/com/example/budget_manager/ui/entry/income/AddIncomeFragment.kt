package com.example.budget_manager.ui.entry.income

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.budget_manager.R
import com.example.budget_manager.databinding.FragmentAddIncomeBinding
import com.example.budget_manager.databinding.FragmentProfileBinding


class AddIncomeFragment : Fragment() {

private lateinit var binding: FragmentAddIncomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentAddIncomeBinding.inflate(inflater,container,false)
        val root=binding.root


        return root
    }


}