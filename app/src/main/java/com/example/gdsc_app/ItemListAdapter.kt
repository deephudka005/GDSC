package com.example.gdsc_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_app.data.Order

class ItemListAdapter():  RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>(){
    private val items:ArrayList<Order> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_list_item,parent,false)
        val viewHoler=ItemViewHolder(view)
        return viewHoler
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = items[position]
        holder.id.text= current.id.toString()
        holder.product.text=current.product_name
        holder.quantity.text=current.quantity
        holder.shining.text=current.shining
        holder.date.text=current.date
    }

    override fun getItemCount(): Int {
        return items.size
    }
    /*class ItemViewHolder(private var binding: ItemListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Order) {
            binding.id.text = item.id
            binding.productinfo.text = item.product_name
            binding.itemQuantity.text = item.quantity
            binding.
        }
    }*/
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val id: TextView =itemView.findViewById(R.id.id)
        val product: TextView =itemView.findViewById(R.id.productinfo)
        val quantity: TextView = itemView.findViewById(R.id.quantityofproduct)
        val shining: TextView= itemView.findViewById(R.id.shiningofproduct)
        val date: TextView= itemView.findViewById(R.id.dateoforder)
    }
}