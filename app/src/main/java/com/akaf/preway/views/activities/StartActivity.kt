package com.akaf.preway.views.activities

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.akaf.preway.MusicName
import com.akaf.preway.R
import com.akaf.preway.model.BeatBox
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : BaseActivity() {


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

        val aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),
            R.anim.rotate_anim
        )
        loginBackgroundDots.startAnimation(aniRotate)
        loginBackgroundShapes.startAnimation(aniRotate)
        getStartedButton.setOnClickListener {

            val intent= FirstVerificationActivity.newIntent(this)
            startActivity(intent)
        }
        play(MusicName.OFFAIR_SPLASH,false)
    }

    fun play(soundName: String, looping: Boolean) {

        beatBox = BeatBox(this)
        mediaPlayer = MediaPlayer()
        for (item in beatBox.soundsList) {
            if (item.soundName.equals(soundName))
                beatBox.play(item, mediaPlayer, looping)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
