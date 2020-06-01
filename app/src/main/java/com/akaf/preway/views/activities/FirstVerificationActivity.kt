package com.akaf.preway.views.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.akaf.preway.R
import com.akaf.preway.model.Login
import com.akaf.preway.service.RetrofitInstance
import com.akaf.preway.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.verification.*
import java.util.EnumSet.of

class FirstVerificationActivity : BaseActivity() {
    lateinit var loginViewModel: LoginViewModel

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, FirstVerificationActivity::class.java)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verification)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val aniRotate = AnimationUtils.loadAnimation(
            getApplicationContext(),
            R.anim.rotate_anim
        )
        verfication_background_image.startAnimation(aniRotate)
        verification_edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                verification_submit_btn.isEnabled = true
            }

        })


        verification_submit_btn.setOnClickListener {

                if (verification_edit_text.text.isEmpty() || verification_edit_text.text.length < 10) {
                    Toast.makeText(this, "لطفا شماره تلفن را به درستی وارد کنید!", Toast.LENGTH_SHORT).show()
                } else {
                    first_verification_progress_bar.visibility= View.VISIBLE
                    verification_submit_btn.visibility=View.GONE
                    signIn()
                }

        }

    }

    fun signIn() {
        var mobile = verification_edit_text.text.trim().toString()
        loginViewModel.singIn(Login(false, "", mobile, "", "", "",""))
        loginViewModel.mLogin?.observe(this, Observer<Login> {
            if (it != null) {
                Toast.makeText(this, "کد تایید برای شما ارسال شد", Toast.LENGTH_SHORT).show()
                val intent = SecondVerificationActivity.newIntent(this,mobile,it.is_first)
                startActivity(intent)
                first_verification_progress_bar.visibility= View.INVISIBLE
                verification_submit_btn.visibility=View.VISIBLE
            }else{
                Toast.makeText(this, "ارسال کد تایید با خطا مواجه شد!", Toast.LENGTH_SHORT).show()
                first_verification_progress_bar.visibility= View.INVISIBLE
                verification_submit_btn.visibility=View.VISIBLE
            }

        })

    }


}
