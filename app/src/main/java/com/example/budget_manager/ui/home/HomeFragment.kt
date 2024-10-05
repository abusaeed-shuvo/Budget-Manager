package com.example.budget_manager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.budget_manager.R
import com.example.budget_manager.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


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