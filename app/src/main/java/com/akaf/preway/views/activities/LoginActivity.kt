package com.akaf.preway.views.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.akaf.preway.R
import com.akaf.preway.SharePreferenceData
import com.akaf.preway.model.Login
import com.akaf.preway.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    lateinit var loginViewModel: LoginViewModel
    lateinit var presenterCode: String

    companion object {
        val PRESENTER_CODE = "presenter_code"
        fun newIntent(context: Context, presenterCode: String): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra(PRESENTER_CODE, presenterCode)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        presenterCode = intent.getStringExtra(PRESENTER_CODE)
        login_checkbox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    login_next_button.isEnabled = true
                }
            }

        })
        login_next_button.setOnClickListener {
            if (login_username_edtxt.text.isEmpty() || login_referal_edtxt.text.isEmpty()) {
                Toast.makeText(this, "لطفا تمامی فیلدهارا پر کنید!", Toast.LENGTH_SHORT).show()
            } else {
                fillInfo()

            }

        }

    }

    fun fillInfo() {
        var token = SharePreferenceData.getToken(this)
        var userName = login_username_edtxt.text.trim().toString()
        loginViewModel.filInfo(token!!, Login(false, "", "", "", userName, "",""))
        loginViewModel.mLogin?.observe(this, Observer {

            if (it != null) {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this,it?.message, Toast.LENGTH_SHORT).show()
            }

        })

    }
}
