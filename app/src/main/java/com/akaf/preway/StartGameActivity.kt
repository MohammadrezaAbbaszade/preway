package com.akaf.preway

import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.Button
import kotlinx.android.synthetic.main.activity_start_game.*
import kotlinx.android.synthetic.main.offair_question_counter_view.*


import kotlinx.android.synthetic.main.offair_start_view.*
import kotlinx.android.synthetic.main.offair_stats_animated_stars.*
import kotlinx.android.synthetic.main.offair_stats_view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import java.io.IOException

class StartGameActivity : AppCompatActivity() {
    val question by lazy {
        Question(this)
    }

    enum class TimerState {
        Stopped, Paused, Running
    }

    lateinit var beatBox: BeatBox
    lateinit var timer: CountDownTimer

    private var timerState = TimerState.Stopped
    private var secondsRemaining = 0L

    private var time = 1

    lateinit var afd: AssetFileDescriptor

    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)

        play("offair_music.mp3",true)


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
        first_answer.setOnClickListener {

            answerClicked(first_answer)
        }
        second_answer.setOnClickListener {

            answerClicked(second_answer)
        }

        third_answer.setOnClickListener {

            answerClicked(third_answer)
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
        offairQuestionCounterView.startAnimation(getAnimation(R.anim.fade_in))
        offairQuestionCounterView.visibility = View.VISIBLE

        offairQuestionCounterValueView.text = "${questionCounter + 1}" + " " + "of 12"

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

        offairQuestionCounterView.startAnimation(getAnimation(R.anim.fade_out))
        offairQuestionCounterView.visibility = View.GONE
        offairQuestionView.visibility = View.VISIBLE
        offairCountdownContainer.visibility = View.VISIBLE
//        offairQuestionTextView.visibility = View.VISIBLE

        var questionCounter = SharePreferenceData.getQuestionCounter(this)!!

        Log.e("seeTheStarts", questionCounter.toString())
        offairQuestionTextView.text = question.questions.get(questionCounter)
        SharePreferenceData.setQuestionCounter(this, (++questionCounter) % 12)

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
//        offairQuestionTextView.visibility = View.INVISIBLE
        offairTimesUpPillView.visibility = View.VISIBLE

        first_answer.isEnabled = false
        second_answer.isEnabled = false
        third_answer.isEnabled = false

        Handler().postDelayed(object : Runnable {
            override fun run() {

                offairQuestionView.startAnimation(getAnimation(R.anim.fade_out))
                offairQuestionView.visibility = View.INVISIBLE
                offairTimesUpPillView.visibility = View.INVISIBLE
                startQuestionCounter()
            }

        }, 2000)


        Handler().postDelayed(object : Runnable {
            override fun run() {
                resetAnswers()
                setQuestionsText()

                startUpdateProgressBar()
            }

        }, 5000)

    }

    private fun checkAnswer(answer: String): Boolean {
        var questionCounter = SharePreferenceData.getQuestionCounter(this)!!
        var correctAnswer = question.answers.get(questionCounter)

        return answer.equals(correctAnswer)

    }

    private fun getAnswer(): String {
        var questionCounter = SharePreferenceData.getQuestionCounter(this)!!
        var correctAnswer = question.answers.get(questionCounter)

        return correctAnswer

    }

    private fun getAnimation(aninationId: Int): Animation {
        val animation = AnimationUtils.loadAnimation(getApplicationContext(), aninationId)
        return animation
    }


    public fun answerClicked(view: Button) {
        timer.cancel()
        time = 1
        offairCountdownContainer.visibility = View.INVISIBLE
        var drawable = resources.getDrawable(R.drawable.round_correct_answer)
        if (checkAnswer(view.text.toString())) {

            offairTrueResultPillView.visibility = View.VISIBLE
            view.background = drawable
            startAndVisibleStarts()
            first_answer.isEnabled = false
            second_answer.isEnabled = false
            third_answer.isEnabled = false


        } else {
            offairResultPillView.visibility = View.VISIBLE

            resetTheviewAfterAnswer()
            when (getAnswer()) {
                first_answer.text -> {
                    first_answer.background = drawable
                }
                second_answer.text -> {
                    second_answer.background = drawable
                }
                third_answer.text -> {
                    third_answer.background = drawable
                }

            }
            drawable = resources.getDrawable(R.drawable.incorrect_answer)
            view.background = drawable
            first_answer.isEnabled = false
            second_answer.isEnabled = false
            third_answer.isEnabled = false
        }
    }

    private fun resetTheviewAfterAnswer() {
        Log.e("resetTheviewAfterAnswer", "yes")

        Handler().postDelayed(object : Runnable {
            override fun run() {

                offairQuestionView.startAnimation(getAnimation(R.anim.fade_out))
                offairQuestionView.visibility = View.INVISIBLE
                offairResultPillView.visibility = View.INVISIBLE
                offairTrueResultPillView.visibility = View.INVISIBLE
                startQuestionCounter()
                resetStarsPosition()
            }

        }, 2000)


        Handler().postDelayed(object : Runnable {
            override fun run() {
                //                    offairResultPillView.startAnimation(getAnimation(R.anim.fade_out))
                offairResultPillView.visibility = View.INVISIBLE



                resetAnswers()

                setQuestionsText()

                startUpdateProgressBar()
            }

        }, 6000)
    }

    fun resetAnswers() {


        var drawable = resources.getDrawable(R.drawable.answer_round)
        first_answer.background = drawable
        second_answer.background = drawable
        third_answer.background = drawable

        first_answer.isEnabled = true
        second_answer.isEnabled = true
        third_answer.isEnabled = true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (timerState.equals(TimerState.Running)) {
            timer.cancel()
        }

        var questionCounter = SharePreferenceData.getQuestionCounter(this)!!

        Log.e("seeTheStarts", questionCounter.toString())
        SharePreferenceData.setQuestionCounter(this, (questionCounter - 1 + 12) % 12)

        mediaPlayer.release()
        beatBox.release(mediaPlayer)
    }

    private fun startAndVisibleStarts() {
        var animatedStar1X = animatedStar1.x
        var animatedStar1Y = animatedStar1.y
        var animatedStar2X = animatedStar2.x
        var animatedStar2Y = animatedStar2.y
        var animatedStar3X = animatedStar3.x
        var animatedStar3Y = animatedStar3.y
        var animatedStar4X = animatedStar4.x
        var animatedStar4Y = animatedStar4.y
        var animatedStar5X = animatedStar5.x
        var animatedStar5Y = animatedStar5.y

        animatedStar1.visibility = View.VISIBLE
        animatedStar2.visibility = View.VISIBLE
        animatedStar3.visibility = View.VISIBLE
        animatedStar4.visibility = View.VISIBLE
        animatedStar5.visibility = View.VISIBLE

        Handler().postDelayed(object : Runnable {
            override fun run() {
                animatedStar1.animate()
                    .x(starView.x)
                    .y(starView.y)
                    .setDuration(500)
                    .start()

                animatedStar2.animate()
                    .x(starView.x)
                    .y(starView.y)
                    .setDuration(600)
                    .start()


                animatedStar3.animate()
                    .x(starView.x)
                    .y(starView.y)
                    .setDuration(700)
                    .start()

                animatedStar4.animate()
                    .x(starView.x)
                    .y(starView.y)
                    .setDuration(800)
                    .start()

                animatedStar5.animate()
                    .x(starView.x)
                    .y(starView.y)
                    .setDuration(900)
                    .start()


            }

        }, 1000)


        resetTheviewAfterAnswer()
        Handler().postDelayed(object : Runnable {
            override fun run() {

                animatedStar1.x = animatedStar1X
                animatedStar1.y = animatedStar1Y
                animatedStar2.x = animatedStar2X
                animatedStar2.y = animatedStar2Y
                animatedStar3.x = animatedStar3X
                animatedStar3.y = animatedStar3Y
                animatedStar4.x = animatedStar4X
                animatedStar4.y = animatedStar4Y
                animatedStar5.x = animatedStar5X
                animatedStar5.y = animatedStar5Y
            }

        }, 4000)

    }

    private fun resetStarsPosition() {
        animatedStar1.visibility = View.INVISIBLE
        animatedStar2.visibility = View.INVISIBLE
        animatedStar3.visibility = View.INVISIBLE
        animatedStar4.visibility = View.INVISIBLE
        animatedStar5.visibility = View.INVISIBLE
    }

    fun play(soundName:String,looping:Boolean) {

        beatBox= BeatBox(this)
        mediaPlayer = MediaPlayer()
        for(item in beatBox.soundsList){
            if(item.soundName.equals(soundName))
                beatBox.play(item,mediaPlayer,looping)
        }
    }

}