package com.akaf.preway


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.footer_item.view.*
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
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerViewImages = ArrayList<Int>()
        recyclerViewImages.add(1)
        initRecyclerView(recyclerViewImages, view)

        view.getHelpButton.setOnClickListener {
            val intent = Intent(activity, HelpActivity::class.java)
            startActivity(intent)
        }
        view.legalButton.setOnClickListener {
            val dialogView =
                getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null)
            val dialog = BottomSheetDialog(context!!)
            dialog.setContentView(dialogView)
            dialog.show()

        }


        view.reviewUsButton.setOnClickListener {

            showDialog(context!!)
        }
        return view
    }

    private fun initRecyclerView(images: List<Int>, view: View) {
        recyclerView =
            Adapter(context!!, images)
        view.main_hq_recycler.adapter = recyclerView

    }


    private fun showDialog(context: Context) {

        val alertDialog = AlertDialog.Builder(context)

        alertDialog.setView(R.layout.rate_us_flow)
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
