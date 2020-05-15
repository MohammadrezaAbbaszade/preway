package com.akaf.preway


import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.leader_board_lest_item.view.*
import kotlinx.android.synthetic.main.store_raw_items.view.*

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

            setItemListener()

            with(view){



            }
        }


        fun setItemListener()
        {
            view.store_items_first.setOnClickListener{
                showDialog(context)

            }

            view.store_items_second.setOnClickListener{
                showDialog(context)

            }

            view.store_items_third.setOnClickListener{
                showDialog(context)

            }
        }


        fun showDialog(context:Context){
            val alertDialog = AlertDialog.Builder(context)

            alertDialog.setView(R.layout.store_need_coin)
            alertDialog.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }


            })
            alertDialog.show()
        }


    }


}