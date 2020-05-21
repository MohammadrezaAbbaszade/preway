package com.akaf.preway.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.akaf.preway.R
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),
            R.anim.rotate_anim
        )
        loginBackgroundDots.startAnimation(aniRotate)
        loginBackgroundShapes.startAnimation(aniRotate)
        getStartedButton.setOnClickListener {

            val intent= Intent(this, FirstVerificationActivity::class.java)
            startActivity(intent)
        }
    }
}
