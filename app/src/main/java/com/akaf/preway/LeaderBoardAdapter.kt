package com.akaf.preway

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.leader_board_lest_item.view.*

class LeaderBoardAdapter(val context: Context, var productList: List<Int>) :
    RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.leader_board_lest_item, parent, false)
        val viewHolder = ViewHolder(view, context)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }


    class ViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {

        fun bind(image:Int){
            with(view){
                leaderboard_rank_text_view.text=image.toString()
            }
        }
    }


}