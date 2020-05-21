package com.akaf.preway.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akaf.preway.R

class Adapter(val context: Context, var productList: List<Int>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.hq_announcement, parent, false)
        val viewHolder =
            ViewHolder(view, context)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return 2
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