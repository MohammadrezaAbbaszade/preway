package com.akaf.preway.views.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.akaf.preway.R

class CashOutActivity : BaseActivity() {


    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, CashOutActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_out)
    }
}
