package com.akaf.preway.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akaf.preway.R
import kotlinx.android.synthetic.main.activity_second_verification.*

class SecondVerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_verification)
        second_step_next_button.setOnClickListener {
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
