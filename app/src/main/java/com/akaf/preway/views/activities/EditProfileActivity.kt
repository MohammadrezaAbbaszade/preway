package com.akaf.preway.views.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import com.akaf.preway.R
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : BaseActivity() {
    lateinit var picker: PersianDatePickerDialog


    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, EditProfileActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        edit_profile_month_edtxt.setFilters((arrayOf<InputFilter>(InputFilterMinMax("1", "12"))))
        edit_profile_day_edtxt.setFilters((arrayOf<InputFilter>(InputFilterMinMax("1", "31"))))
    }

}
