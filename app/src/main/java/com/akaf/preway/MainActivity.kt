package com.akaf.preway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.main_hq_layout.*
import kotlinx.android.synthetic.main.main_hq_layout.view.*

class MainActivity : AppCompatActivity() {
private lateinit var recyclerView:Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_hq_layout)
        val recyclerViewImages = ArrayList<Int>()
        recyclerViewImages.add(1)
        initRecyclerView(recyclerViewImages)
    }

    private fun initRecyclerView(images: List<Int>) {
        recyclerView =
            Adapter(this, images)
       main_hq_recycler.adapter = recyclerView

    }
}
