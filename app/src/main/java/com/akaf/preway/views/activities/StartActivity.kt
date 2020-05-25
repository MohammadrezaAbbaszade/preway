package com.akaf.preway.views.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.akaf.preway.R
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity() {



    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, StartActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),
            R.anim.rotate_anim
        )
        loginBackgroundDots.startAnimation(aniRotate)
        loginBackgroundShapes.startAnimation(aniRotate)
        getStartedButton.setOnClickListener {

            val intent= FirstVerificationActivity.newIntent(this)
            startActivity(intent)
        }
    }
}
