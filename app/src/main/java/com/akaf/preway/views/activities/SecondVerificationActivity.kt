package com.akaf.preway.views.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akaf.preway.R
import kotlinx.android.synthetic.main.activity_second_verification.*

class SecondVerificationActivity : BaseActivity() {


    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, SecondVerificationActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_verification)
        second_step_next_button.setOnClickListener {
            val intent= LoginActivity.newIntent(this)
            startActivity(intent)
        }
    }
}
