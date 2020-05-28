package com.akaf.preway.views.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import com.akaf.preway.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {


    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }
    }
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
