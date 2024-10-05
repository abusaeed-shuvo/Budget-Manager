package com.example.budget_manager.ui.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budget_manager.data.Transaction
import com.example.budget_manager.databinding.TransactionItemBinding

class TransactionAdapter(var transactionClicker: TransactionClicker) :
    ListAdapter<Transaction, TransactionViewHolder>(comparator) {

    interface TransactionClicker {
        fun onTransactionClick(transaction: Transaction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            TransactionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        getItem(position).let {
            holder.binding.apply {
                transactionTitle.text = it.title
                transactionAmount.text = it.amount.toString()
            }

        }

    }


    companion object {
        var comparator = object : DiffUtil.ItemCallback<Transaction>() {

            override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
                return oldItem == newItem
            }
        }

    }

}

class TransactionViewHolder(val binding: TransactionItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

}