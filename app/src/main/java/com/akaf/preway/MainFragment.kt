package com.akaf.preway


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    private lateinit var recyclerView: Adapter

    companion object {
        fun newInstance(): MainFragment {
            val args = Bundle()
            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerViewImages = ArrayList<Int>()
        recyclerViewImages.add(1)
        initRecyclerView(recyclerViewImages,view)

        return view
    }

    private fun initRecyclerView(images: List<Int>,view:View) {
        recyclerView =
            Adapter(context!!, images)
        view.main_hq_recycler.adapter = recyclerView

    }

}
