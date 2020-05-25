package com.akaf.preway.views.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import com.akaf.preway.R
import kotlinx.android.synthetic.main.verification.*

class FirstVerificationActivity : AppCompatActivity() {


    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, FirstVerificationActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verification)
        val aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),
            R.anim.rotate_anim
        )
        verfication_background_image.startAnimation(aniRotate)
        verification_edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                verification_submit_btn.isClickable=true
            }

        })


        verification_submit_btn.setOnClickListener {
            val intent= SecondVerificationActivity.newIntent(this)
            startActivity(intent)
        }

    }
}
