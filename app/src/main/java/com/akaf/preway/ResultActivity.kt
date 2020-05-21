package com.akaf.preway

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    lateinit var beatBox: BeatBox
    lateinit var mediaPlayer: MediaPlayer
    companion object {
        private val CORECT_ANSWER = "correctAnswer"
        private val ICORECT_ANSWER = "incorrectAnswer"
        fun newIntent(context: Context, correctAnswer:Int, incorrectAnswer:Int): Intent {
            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra(CORECT_ANSWER,correctAnswer)
            intent.putExtra(ICORECT_ANSWER,incorrectAnswer)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val correctAnswer=intent.getIntExtra(CORECT_ANSWER,0)
        val incorrectAnswer=intent.getIntExtra(ICORECT_ANSWER,0)
        resultsCorrectCountView.text=correctAnswer.toString()
        resultsIncorrectCountView.text=incorrectAnswer.toString()
        play("offair_results.mp3",true)
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
