package com.akaf.preway.views.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akaf.preway.R
import com.akaf.preway.adapter.StoreAdapter
import com.akaf.preway.views.activities.WebStoreActivity
import kotlinx.android.synthetic.main.fragment_store.view.*
import kotlinx.android.synthetic.main.store_header.view.*

/**
 * A simple [Fragment] subclass.
 */
class StoreFragment : Fragment() {

    private lateinit var recyclerView: StoreAdapter
    companion object {
        fun newInstance(): StoreFragment {
            val args = Bundle()
            val fragment = StoreFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_store, container, false)
        val recyclerViewImages = ArrayList<Int>()
        recyclerViewImages.add(1)
//        initRecyclerView(recyclerViewImages,view)

//        view.webStoreButton.setOnClickListener {
//            val intent = WebStoreActivity.newIntent(context!!)
//            startActivity(intent)
//        }

        return view
    }
//    private fun initRecyclerView(images: List<Int>,view:View) {
//        recyclerView =
//            StoreAdapter(context!!, images)
//        view.store_fragment_recycler.adapter = recyclerView
//
//    }

}
