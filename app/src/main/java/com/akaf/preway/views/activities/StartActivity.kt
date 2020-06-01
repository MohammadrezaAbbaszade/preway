package com.akaf.preway.views.activities

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.akaf.preway.MusicName
import com.akaf.preway.R
import com.akaf.preway.SharePreferenceData
import com.akaf.preway.model.BeatBox
import com.akaf.preway.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : BaseActivity() {

    lateinit var loginViewModel: LoginViewModel
    lateinit var beatBox: BeatBox
    lateinit var mediaPlayer: MediaPlayer

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, StartActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        mediaPlayer = MediaPlayer()
        if (SharePreferenceData.getToken(this) != null) {
            val intent = MainActivity.newIntent(this)
            startActivity(intent)
            finish()
        }else {
            val aniRotate = AnimationUtils.loadAnimation(
                getApplicationContext(),
                R.anim.rotate_anim
            )
            loginBackgroundDots.startAnimation(aniRotate)
            loginBackgroundShapes.startAnimation(aniRotate)
            getStartedButton.setOnClickListener {

                val intent = FirstVerificationActivity.newIntent(this)
                startActivity(intent)
            }
            play(MusicName.OFFAIR_SPLASH, false)
        }
    }

    fun play(soundName: String, looping: Boolean) {

        beatBox = BeatBox(this)
        for (item in beatBox.soundsList) {
            if (item.soundName.equals(soundName))
                beatBox.play(item, mediaPlayer, looping)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

//    fun chekTheToken() {
//        var token=SharePreferenceData.getToken(this)
//        loginViewModel.checkInfo(token!!)
//        loginViewModel.mCheckInfo.observe(this, Observer {
//            if(it!==null){
//                if(!it.error.isEmpty()){
//                    val intent = FirstVerificationActivity.newIntent(this)
//                    startActivity(intent)
//                    finish()
//                }else{
//
//                }
//            }
//        })
//
//    }
}
