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
        view.leaderboar_year_edtxt.setText("1399")
//        view.leaderboar_year_edtxt.setFilters((arrayOf<InputFilter>(InputFilterMinMax(1399, 1500))))
        view.leaderboar_day_edtxt.setFilters((arrayOf<InputFilter>(InputFilterMinMax("1", "31"))))
        view.leaderboar_month_edtxt.setFilters((arrayOf<InputFilter>(InputFilterMinMax("1", "12"))))

        view.leaderboar_year_edtxt.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("leaderboard","onTextChanged")
                Log.d("leaderboard",s.toString())
                Log.d("leaderboard",view.leaderboar_year_edtxt.text.toString().length.toString())
            }


        })
//
//        view.leaderboar_month_edtxt.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if(!s.toString().equals("")) {
//
//                    var inputCheck = Integer.parseInt(s.toString())
//                    if (inputCheck < 1 || inputCheck > 12 || s.toString().equals("")) {
//                        view.leaderboar_month_edtxt.setError("لطفا ماه را درست وارد کنید")
//                    }
//                }
//            }
//
//
//        })

        return view
    }

    private fun initRecyclerView(images: List<Int>, view: View) {
        recyclerView =
            LeaderBoardAdapter(context!!, images)
        view.leaderboard_recycler.adapter = recyclerView

    }

}
