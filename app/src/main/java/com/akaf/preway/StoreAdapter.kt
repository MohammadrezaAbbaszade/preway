package com.akaf.preway


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.leader_board_lest_item.view.*

class StoreAdapter(val context: Context, var productList: List<Int>) :
    RecyclerView.Adapter<StoreAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.store_raw_items, parent, false)
        val viewHolder = ViewHolder(view, context)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }


    class ViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {

        fun bind(image:Int){
            with(view){
            }
        }
    }


}