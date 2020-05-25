package com.akaf.preway.views.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akaf.preway.R
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {
lateinit var picker: PersianDatePickerDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        edit_profile_date.setOnClickListener {
            showPersianDate()


        }

    }

    private fun showPersianDate(){
        picker =  PersianDatePickerDialog(this)
            .setPositiveButtonString("باشه")
            .setNegativeButton("بیخیال")
            .setTodayButton("امروز")
            .setTodayButtonVisible(true)
            .setMinYear(1300)
            .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
            .setActionTextColor(Color.GRAY)
            .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
            .setShowInBottomSheet(true)
            .setListener(object : Listener {
                override fun onDismissed() {
                    TODO("Not yet implemented")
                }

                override fun onDateSelected(persianCalendar: PersianCalendar?) {
                    var date=""+ persianCalendar?.persianYear + "/" + persianCalendar?.getPersianMonth() + "/" + persianCalendar?.getPersianDay()
                    edit_profile_date.text=date
                }

            });

        picker.show()

    }
}


