package com.akaf.preway.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import com.akaf.preway.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_checkbox.setOnCheckedChangeListener(object :CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    login_next_button.isEnabled=true
                }
            }

        })
        login_next_button.setOnClickListener {

            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
