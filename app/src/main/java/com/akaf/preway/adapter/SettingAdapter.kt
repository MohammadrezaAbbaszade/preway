package com.akaf.preway.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.akaf.preway.R
import kotlinx.android.synthetic.main.store_raw_items.view.*

class SettingAdapter(val context: Context, var productList: List<Int>) :
    RecyclerView.Adapter<SettingAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.setting_recycler_item, parent, false)
        val viewHolder =
            ViewHolder(view, context)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return 12
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }


    class ViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {


        fun bind(image: Int) {

            with(view) {


            }
        }



    }


}