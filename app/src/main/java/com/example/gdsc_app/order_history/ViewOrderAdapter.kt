package com.example.gdsc_app.order_history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_app.R
import com.example.gdsc_app.data.data_class.Order
import kotlinx.android.synthetic.main.item_list_item.view.*

class ViewOrderAdapter (private val context: ViewOrderFragment): RecyclerView.Adapter<ViewOrderAdapter.OrderViewHolder>(){
    val allNotes = ArrayList<Order>()
    //private var binding: ItemListItemBinding? = null
    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val id= itemView.findViewById<TextView>(R.id.id1)
        val productinfo = itemView.findViewById<TextView>(R.id.productinfo)
        val quantity= itemView.findViewById<TextView>(R.id.quantityofproduct)
        val shining= itemView.findViewById<TextView>(R.id.shiningofproduct)
        val date= itemView.findViewById<TextView>(R.id.dateoforder)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val viewHolder = OrderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_item,parent,false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val current = allNotes[position]
        holder.itemView.id1.text = current.id.toString()
        holder.itemView.productinfo.text= current.product_name
        holder.itemView.quantityofproduct.text=current.quantity
        holder.itemView.shiningofproduct.text=current.shining
        holder.itemView.dateoforder.text=current.date
    }
    fun setData(order:List<Order>){

        allNotes.clear()
        allNotes.addAll(order)
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return allNotes.size
    }
    /*companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem.product_name == newItem.product_name
            }
        }
    }*/
}