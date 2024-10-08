package com.example.budget_manager.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budget_manager.R
import com.example.budget_manager.data.transaction.Transaction
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
            val context = holder.itemView.context
            val showDate = "${it.date}/${it.month + 1}/${it.year}"
            holder.binding.apply {
                transactionTitle.text = it.title
                transactionAmount.text = it.amount.toString()

                transactionDate.text = showDate

                if (it.transactionType == 0) {
                    transactionItem.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.debt
                        )
                    )
                    transactionTypeIcon.setImageResource(R.drawable.baseline_arrow_back_24)
                } else {
                    transactionItem.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.credit
                        )
                    )
                    transactionTypeIcon.setImageResource(R.drawable.baseline_arrow_forward_24)

                }
            }
            holder.itemView.setOnClickListener { _ ->
                transactionClicker.onTransactionClick(it)
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