package com.akaf.preway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import kotlinx.android.synthetic.main.activity_start_game.*
import kotlinx.android.synthetic.main.offair_question_counter_view.*


import kotlinx.android.synthetic.main.offair_start_view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class StartGameActivity : AppCompatActivity() {
    val question by lazy {
        Question(this)
    }

    enum class TimerState {
        Stopped, Paused, Running
    }

    lateinit var timer: CountDownTimer

    private var timerState = TimerState.Stopped
    private var secondsRemaining = 0L

    private var time = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)



        if (!SharePreferenceData.getPlayerResult(this)!!) {
            startButton.text = resources.getText(R.string.play_now)
        } else {
            startButton.text = resources.getText(R.string.Continue)
        }

        startButton.setOnClickListener {
            SharePreferenceData.setPlayerResult(this, true)
            dailyChallengeTextView.visibility = View.GONE
            startButton.visibility = View.GONE
            offairQuestionView.visibility = View.INVISIBLE
            startQuestionCounter()

        }



        Handler().postDelayed(object : Runnable {
            override fun run() {

                setQuestionsText()

                startAnimating()

                startUpdateProgressBar()
            }

        }, 4000)


    }

    private fun startQuestionCounter() {
        Log.e("seeTheStarts", "startQuestionCounter")
        var questionCounter = SharePreferenceData.getQuestionCounter(this)!!

        offairQuestionCounterView.visibility = View.VISIBLE

        offairQuestionCounterValueView.text = "of 12"

    }

    private fun startAnimating() {

        Log.e("seeTheStarts", "startAnimating")
        val makeVertical =
            RotateAnimation(270F, 270F, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        makeVertical.setFillAfter(true)
        offairCountdownProgressBar.startAnimation(makeVertical)
        offairCountdownProgressBar.setSecondaryProgress(10)
        offairCountdownProgressBar.setProgress(0)
    }

    private fun setQuestionsText() {
        Log.e("seeTheStarts", "setQuestionsText")
        offairQuestionCounterView.visibility = View.GONE
        offairQuestionView.visibility = View.VISIBLE
        offairCountdownContainer.visibility = View.VISIBLE
        offairQuestionTextView.visibility = View.VISIBLE

        var questionCounter = SharePreferenceData.getQuestionCounter(this)!!
        if (questionCounter <= 11) {

            offairQuestionTextView.text = question.questions.get(questionCounter)
            SharePreferenceData.setQuestionCounter(this, questionCounter + 1)
        } else {
            SharePreferenceData.setQuestionCounter(this, 0)
        }
    }

    private fun startUpdateProgressBar() {
        Log.e("seeTheStarts", "startUpdateProgressBar")
        timerState = TimerState.Running

        timer = object : CountDownTimer(10 * 1000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                Log.e("timeown", time.toString() + " /" + millisUntilFinished.toString())
                secondsRemaining = millisUntilFinished / 1000
                updateTimerTextView(time, 10)
                time++
            }

            override fun onFinish() {
                Log.e("timeown", "finished")
                timerState = TimerState.Stopped
                time = 1

                timesUp()
            }


        }
        timer.start()

    }

    private fun updateTimerTextView(startTime: Int, endTime: Int) {
        offairCountdownTextView.text = startTime.toString()
        offairCountdownProgressBar.setMax(endTime)
        offairCountdownProgressBar.setSecondaryProgress(endTime)
        offairCountdownProgressBar.setProgress(startTime)
    }

    private fun timesUp() {
        Log.e("seeTheStarts", "timesUp")

        offairCountdownContainer.visibility = View.INVISIBLE
        offairQuestionTextView.visibility = View.INVISIBLE
        offairTimesUpPillView.visibility = View.VISIBLE

        Handler().postDelayed(object : Runnable {
            override fun run() {

                offairTimesUpPillView.startAnimation(getAnimation(R.anim.fade_out))
                offairTimesUpPillView.visibility = View.INVISIBLE
                startQuestionCounter()
            }

        }, 2000)


        Handler().postDelayed(object : Runnable {
            override fun run() {


                setQuestionsText()

                startUpdateProgressBar()
            }

        }, 5000)

    }

    private fun checkAnswer() {


    }

    private fun getAnimation(aninationId: Int): Animation {
        val animation = AnimationUtils.loadAnimation(getApplicationContext(), aninationId)
        return animation
    }

}