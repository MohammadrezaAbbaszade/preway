package com.akaf.preway.views.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akaf.preway.R
import com.akaf.preway.adapter.Adapter
import com.akaf.preway.adapter.SettingAdapter
import com.akaf.preway.views.activities.CashOutActivity
import com.akaf.preway.views.activities.WebStoreActivity
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_setting.view.*
import kotlinx.android.synthetic.main.setting_header.view.*


/**
 * A simple [Fragment] subclass.
 */
class SettingFragment : Fragment() {
    private lateinit var recyclerView: SettingAdapter

    companion object {
        fun newInstance(): SettingFragment {
            val args = Bundle()
            val fragment = SettingFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_setting, container, false)
        view.setting_progress_bar.progress=50
        view.setting_progress_bar.max=100
        view.setting_progress_bar.secondaryProgress=100

        val recyclerViewImages = ArrayList<Int>()
        recyclerViewImages.add(1)
        initRecyclerView(recyclerViewImages, view)

        view.setting_cashout.setOnClickListener {
            val intent = Intent(activity, CashOutActivity::class.java)
            startActivity(intent)

        }
        return view
    }

    private fun initRecyclerView(images: List<Int>, view: View) {
        recyclerView =
            SettingAdapter(context!!, images)
        view.setting_recycler.adapter = recyclerView

    }
}
