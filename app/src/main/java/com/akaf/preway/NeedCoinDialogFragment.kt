package com.akaf.preway


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

/**
 * A simple [Fragment] subclass.
 */
class NeedCoinDialogFragment : DialogFragment() {


    companion object {
        fun newInstance(): NeedCoinDialogFragment {
            val args = Bundle()
            val fragment = NeedCoinDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_need_coin_dialog, container, false)


        return view
    }


}
