package com.akaf.preway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.verification.*

class FirstVerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verification)
        val aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anim)
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
            val intent= Intent(this, SecondVerificationActivity::class.java)
            startActivity(intent)
        }

    }
}
