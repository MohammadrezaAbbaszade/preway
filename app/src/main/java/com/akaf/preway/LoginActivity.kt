package com.akaf.preway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anim)
        loginBackgroundDots.startAnimation(aniRotate)
        loginBackgroundShapes.startAnimation(aniRotate)
        getStartedButton.setOnClickListener {

            val intent= Intent(this, FirstVerificationActivity::class.java)
            startActivity(intent)
        }
    }
}
