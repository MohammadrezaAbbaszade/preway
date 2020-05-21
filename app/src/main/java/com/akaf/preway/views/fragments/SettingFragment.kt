package com.akaf.preway.views.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akaf.preway.R

/**
 * A simple [Fragment] subclass.
 */
class SettingFragment : Fragment() {


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


        return view
    }


}
