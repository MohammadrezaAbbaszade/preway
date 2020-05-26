package com.akaf.preway.adapter


import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.akaf.preway.R
import kotlinx.android.synthetic.main.store_raw_items.view.*

class StoreAdapter(val context: Context, var productList: List<Int>) :
    RecyclerView.Adapter<StoreAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.store_raw_items, parent, false)
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


        fun bind(image: Int) {

            with(view) {
                if(image==1) {
                    store_item_img1.background=context.resources.getDrawable(R.drawable.ic_single_coins)
                    store_item_img2.background=context.resources.getDrawable(R.drawable.ic_single_coins)
                    store_item_img3.background=context.resources.getDrawable(R.drawable.ic_single_coins)
                }

            }
        }

        fun showDialog(context: Context) {
            val alertDialog = AlertDialog.Builder(context)

            alertDialog.setView(R.layout.fragment_need_coin_dialog)
            alertDialog.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }


            })
            alertDialog.show()

        }


    }


}