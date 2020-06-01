package com.akaf.preway.views.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.akaf.preway.R
import com.akaf.preway.SharePreferenceData
import com.akaf.preway.model.Login
import com.akaf.preway.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_second_verification.*
import kotlinx.android.synthetic.main.verification.*

class SecondVerificationActivity : BaseActivity() {
    lateinit var loginViewModel: LoginViewModel
    lateinit var mobileNumber: String

    companion object {
        val MOBILE = "mobile"
        val IS_FIRST = "isFirst"
        fun newIntent(context: Context, mobile: String, isFirst: Boolean): Intent {
            val intent = Intent(context, SecondVerificationActivity::class.java)
            intent.putExtra(MOBILE, mobile)
            intent.putExtra(IS_FIRST, isFirst)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_verification)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        mobileNumber = intent.getStringExtra(MOBILE)

        verification_code.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                second_step_next_button.isEnabled = true
            }

        })
        second_step_next_button.setOnClickListener {
            if (verification_code.text.isEmpty() || verification_code.text.length < 5) {
                Toast.makeText(this, "لطفا کد تاییدیه را به درستی وارد کنید!", Toast.LENGTH_SHORT).show()
            } else {
                verification_progress_bar.visibility= View.VISIBLE
                second_step_next_button.visibility=View.INVISIBLE
                verifyMobile()
            }

        }
    }

    fun verifyMobile() {
        var auth_code = verification_code.text.trim().toString()
        loginViewModel.verifyMobile(Login(false, "", mobileNumber, auth_code, "", "",""))
        loginViewModel.mVerifyMobile?.observe(this, Observer {
            if (it != null) {
                    SharePreferenceData.setToken(this, "bearer "+it.token)
                    if (intent.getBooleanExtra(IS_FIRST, false)) {
                        val intent = LoginActivity.newIntent(this, "it.user.present_code")
                        startActivity(intent)
                    } else {
                        val intent = MainActivity.newIntent(this)
                        startActivity(intent)
                        finish()
                    }
                verification_progress_bar.visibility= View.GONE
                second_step_next_button.visibility=View.VISIBLE
            } else {
                Toast.makeText(this, "کد تاییدیه مورد قبول قرار نگرفت", Toast.LENGTH_SHORT).show()
                verification_progress_bar.visibility= View.GONE
                second_step_next_button.visibility=View.VISIBLE
            }


        })

    }
}
