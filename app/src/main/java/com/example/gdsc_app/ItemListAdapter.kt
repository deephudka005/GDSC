package com.example.gdsc_app

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Index
import com.example.gdsc_app.data.Order
import com.example.gdsc_app.databinding.FragmentVieworderBinding
import com.google.android.ads.mediationtestsuite.viewmodels.ItemViewHolder

class ItemListAdapter():  ListAdapter<Order,ItemListAdapter.ItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            FragmentVieworderBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }
    class ItemViewHolder(private var binding: ItemListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Order) {
            binding.id.text = item.id
            binding.productinfo.text = item.product_name
            binding.quantityofproduct.text = item.quantity
            binding.shiningofproduct.text= item.shining
            binding.dateoforder.text = item.date
        }
    }
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem.itemName == newItem.itemName
            }
        }
    }
}