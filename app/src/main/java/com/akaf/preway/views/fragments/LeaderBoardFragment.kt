package com.akaf.preway.views.fragments


import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.akaf.preway.R
import com.akaf.preway.adapter.LeaderBoardAdapter
import com.akaf.preway.views.activities.InputFilterMinMax
import kotlinx.android.synthetic.main.fragment_leaderboard.view.*


/**
 * A simple [Fragment] subclass.
 */
class LeaderBoardFragment : Fragment() {
    private lateinit var recyclerView: LeaderBoardAdapter

    companion object {
        fun newInstance(): LeaderBoardFragment {
            val args = Bundle()
            val fragment = LeaderBoardFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_leaderboard, container, false)
        val recyclerViewImages = ArrayList<Int>()
        recyclerViewImages.add(1)
        initRecyclerView(recyclerViewImages, view)

        return view
    }

    private fun initRecyclerView(images: List<Int>, view: View) {
        recyclerView =
            LeaderBoardAdapter(context!!, images)
        view.leaderboard_recycler.adapter = recyclerView

    }

}
