package com.akaf.preway.views.activities

import android.content.Context
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.Button
import com.akaf.preway.*
import com.akaf.preway.model.BeatBox
import com.akaf.preway.model.Question
import kotlinx.android.synthetic.main.activity_start_game.*
import kotlinx.android.synthetic.main.offair_question_counter_view.*


import kotlinx.android.synthetic.main.offair_start_view.*
import kotlinx.android.synthetic.main.offair_stats_animated_stars.*
import kotlinx.android.synthetic.main.offair_stats_view.*

class StartGameActivity : BaseActivity() {
    val question by lazy {
        Question(this)
    }
    var handler = Handler()

    enum class TimerState {
        Stopped, Paused, Running
    }

    lateinit var beatBox: BeatBox
    lateinit var timer: CountDownTimer

    private var timerState =
        TimerState.Stopped
    private var secondsRemaining = 0L
    private var isLiveShow = false
    private var time = 1
    private var questionCounter = 0
    private var correctAnswerCount = 0
    private var incorrectAnswerCount = 0
    lateinit var afd: AssetFileDescriptor
    lateinit var view: Button
    lateinit var mediaPlayer: MediaPlayer
    var mediaPlayer2: MediaPlayer? = null

    companion object {
        private val CHECK_LIVESHOW = "checkliveshow"
        fun newIntent(context: Context, checkLiveShow: Boolean): Intent {
            val intent = Intent(context, StartGameActivity::class.java)
            intent.putExtra(CHECK_LIVESHOW, checkLiveShow)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        isLiveShow = intent.getBooleanExtra(CHECK_LIVESHOW, false)
        play(MusicName.OFFAIR_MUSCI, true)
        if (isLiveShow) {
            dailyChallengeTextView.text = "???????? ????"
        } else {
            dailyChallengeTextView.text = "???????? ????????????"
        }

        if (!SharePreferenceData.getPlayerResult(this)!!) {
            startButton.text = "???????? ????"
        } else {
            startButton.text = "??????????"
        }

        startButton.setOnClickListener {
            SharePreferenceData.setPlayerResult(
                this,
                true
            )
            dailyChallengeTextView.visibility = View.GONE
            startButton.visibility = View.GONE
            offairQuestionView.visibility = View.INVISIBLE

            startQuestionCounter()
            handler.postDelayed(object : Runnable {
                override fun run() {

                    setQuestionsText()

                    startUpdateProgressBar()
                }

            }, 4000)
        }
        first_answer.setOnClickListener {
            view = first_answer
            answerClicked(first_answer)
        }
        second_answer.setOnClickListener {
            view = second_answer
            answerClicked(second_answer)
        }

        third_answer.setOnClickListener {
            view = third_answer
            answerClicked(third_answer)
        }
        fourth_answer.setOnClickListener {
            view = fourth_answer
            answerClicked(fourth_answer)
        }
        power_ups_delete.setOnClickListener {

            resetAnswers()
            power_ups_delete.isEnabled = false
        }

    }

    private fun startQuestionCounter() {
        Log.e("seeTheStarts", "startQuestionCounter")
        Log.e("checkMedi", "startQuestionCounter")
        var questionCounter = SharePreferenceData.getQuestionCounter(
            this
        )!!
        offairQuestionCounterView.startAnimation(getAnimation(R.anim.fade_in))
        offairQuestionCounterView.visibility = View.VISIBLE

        offairQuestionCounterValueView.text = "${questionCounter + 1}" + " " + "????" + " " + "12"

    }


    private fun setQuestionsText() {
        Log.e("checkMedi", "setQuestionsText")
        offairQuestionCounterView.startAnimation(getAnimation(R.anim.fade_out))
        offairQuestionCounterView.visibility = View.GONE
        offairQuestionView.visibility = View.VISIBLE
        offairCountdownContainer.visibility = View.VISIBLE
        if (isLiveShow) {
            powerups_buttons.visibility = View.VISIBLE
        } else {
            start_game_powerup_buttons.visibility = View.VISIBLE
        }

        var questionCounter = SharePreferenceData.getQuestionCounter(
            this
        )!!

        Log.e("seeTheStarts", questionCounter.toString())
        offairQuestionTextView.setTextAutoTyping(question.questions.get(questionCounter))
        SharePreferenceData.setQuestionCounter(
            this,
            (++questionCounter) % 12
        )

    }

    private fun startUpdateProgressBar() {
        Log.e("checkMedi", "startUpdateProgressBar")
        Log.e("seeTheStarts", "startUpdateProgressBar")
        playOtherSound(MusicName.OFFAIR_TIMER, false)
        offairCountdownProgressBar.progress = 0f
        offairCountdownProgressBar.setProgressWithAnimation(100f, 10000)
        timerState =
            TimerState.Running

        timer = object : CountDownTimer(10 * 1000, 1000) {

            override fun onTick(millisUntilFinished: Long) {

                Log.e("timeown", time.toString() + " /" + millisUntilFinished.toString())
                secondsRemaining = millisUntilFinished / 1000
                updateTimerTextView(time, 10)
                time++
            }

            override fun onFinish() {
                Log.e("timeown", "finished")
                timerState =
                    TimerState.Stopped
                time = 1

                timesUp()
            }


        }
        timer.start()

    }

    private fun updateTimerTextView(startTime: Int, endTime: Int) {
        offairCountdownTextView.text = startTime.toString()
    }

    private fun timesUp() {
        Log.e("seeTheStarts", "timesUp")
        Log.e("checkMedi", "timesUp")
        mediaPlayer2?.release()

        offairCountdownContainer.visibility = View.INVISIBLE
        offairCountdownTextView.text = ""


        offairCountdownProgressBar.progress = 0f
        disableAnswerButtons()

        if (isLiveShow && power_ups_delete.isEnabled) {
            power_ups_delete.isEnabled = false
            checkAndSetBackGroundForAnswers(view)
        } else {

            offairTimesUpPillView.visibility = View.VISIBLE
            handler.postDelayed(object : Runnable {
                override fun run() {
                    Log.e("checkMedi", "timesUp1")
                    offairQuestionView.startAnimation(getAnimation(R.anim.fade_out))
                    offairQuestionView.visibility = View.INVISIBLE
                    offairTimesUpPillView.visibility = View.INVISIBLE
                    offairQuestionTextView.text = ""
                    startQuestionCounter()
                }

            }, 2000)


            handler.postDelayed(object : Runnable {
                override fun run() {
                    Log.e("checkMedi", "timesUp2")
                    resetAnswers()
                    setQuestionsText()

                    startUpdateProgressBar()
                }

            }, 5000)
        }

    }

    private fun checkAnswer(answer: String): Boolean {
        Log.e("checkMedi", "checkAnswer")
        var questionCounter = SharePreferenceData.getQuestionCounter(
            this
        )!!
        var correctAnswer = question.answers.get(questionCounter)

        return answer.equals(correctAnswer)

    }

    private fun getAnswer(): String {
        Log.e("checkMedi", "getAnswer")
        var questionCounter = SharePreferenceData.getQuestionCounter(
            this
        )!!
        var correctAnswer = question.answers.get(questionCounter)

        return correctAnswer

    }

    private fun getAnimation(aninationId: Int): Animation {
        val animation = AnimationUtils.loadAnimation(getApplicationContext(), aninationId)
        return animation
    }


    public fun answerClicked(view: Button) {
        Log.e("checkMedi", "answerClicked")
        var drawable = resources.getDrawable(R.drawable.button_round)
        if (isLiveShow) {
            view.background = drawable
            power_ups_delete.isEnabled = true
            disableAnswerButtons()

        } else {
            mediaPlayer2?.release()
            timer.cancel()
            time = 1
            questionCounter++
            offairCountdownContainer.visibility = View.INVISIBLE
            checkAndSetBackGroundForAnswers(view)
        }
    }

    private fun checkAndSetBackGroundForAnswers(view: Button) {
        var drawable1 = resources.getDrawable(R.drawable.round_correct_answer)
        if (checkAnswer(view.text.toString())) {
            playOtherSound(MusicName.OFFAIR_CORRECT, false)
            correctAnswerCount++
            offairTrueResultPillView.visibility = View.VISIBLE
            view.background = drawable1

            startAndVisibleStarts()
            disableAnswerButtons()

        } else {
            playOtherSound(MusicName.OFFAIR_INCORRECT, false)
            incorrectAnswerCount++
            offairResultPillView.visibility = View.VISIBLE

            resetTheviewAfterAnswer()
            when (getAnswer()) {
                first_answer.text -> {
                    first_answer.background = drawable1
                }
                second_answer.text -> {
                    second_answer.background = drawable1
                }
                third_answer.text -> {
                    third_answer.background = drawable1
                }
                fourth_answer.text -> {
                    fourth_answer.background = drawable1
                }

            }
            drawable1 = resources.getDrawable(R.drawable.incorrect_answer)
            view.background = drawable1
            disableAnswerButtons()
        }
    }

    private fun disableAnswerButtons() {
        first_answer.isEnabled = false
        second_answer.isEnabled = false
        third_answer.isEnabled = false
        fourth_answer.isEnabled = false
    }

    private fun resetTheviewAfterAnswer() {
        Log.e("resetTheviewAfterAnswer", "yes")
        Log.e("checkMedi", "resetTheviewAfterAnswer")
        handler.postDelayed(object : Runnable {
            override fun run() {
                Log.e("checkMedi", "resetTheviewAfterAnswer1")
                offairQuestionView.startAnimation(getAnimation(R.anim.fade_out))
                offairQuestionView.visibility = View.INVISIBLE
                offairResultPillView.visibility = View.INVISIBLE
                offairTrueResultPillView.visibility = View.INVISIBLE
                if (questionCounter == 12) {
                    tasksAfterDestroying()
                    goToResultActivity()
                    return
                }
                startQuestionCounter()
                resetStarsPosition()
            }

        }, 2000)


        handler.postDelayed(object : Runnable {
            override fun run() {
                Log.e("checkMedi", "resetTheviewAfterAnswer2")
                offairResultPillView.visibility = View.INVISIBLE



                resetAnswers()

                setQuestionsText()

                startUpdateProgressBar()
            }

        }, 6000)
    }

    fun resetAnswers() {

        Log.e("checkMedi", "resetAnswers")
        var drawable = resources.getDrawable(R.drawable.answer_round)
        first_answer.background = drawable
        second_answer.background = drawable
        third_answer.background = drawable
        fourth_answer.background = drawable

        first_answer.isEnabled = true
        second_answer.isEnabled = true
        third_answer.isEnabled = true
        fourth_answer.isEnabled = true
    }

    override fun onDestroy() {
        super.onDestroy()
        tasksAfterDestroying()
    }

    private fun tasksAfterDestroying() {
        Log.e("checkMedi", "tasksAfterDestroying")
        handler.removeCallbacksAndMessages(null)

        if (timerState.equals(TimerState.Running)) {
            timer.cancel()
        }

        var questionCounter = SharePreferenceData.getQuestionCounter(
            this
        )!!

        Log.e("seeTheStarts", questionCounter.toString())
        SharePreferenceData.setQuestionCounter(
            this,
            (questionCounter - 1 + 12) % 12
        )


        beatBox.release(mediaPlayer)
        if (mediaPlayer2 != null) {
            beatBox.release(mediaPlayer2!!)
        }
    }

    private fun startAndVisibleStarts() {
        Log.e("checkMedi", "startAndVisibleStarts")
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

        handler.postDelayed(object : Runnable {
            override fun run() {
                Log.e("checkMedi", "startAndVisibleStarts1")
                playOtherSound(MusicName.OFFAIR_POINTS, false)
                animatedStar1.animate()
                    .x(starView.x)
                    .y(starView.y)
                    .rotationBy(36000F)
                    .setDuration(500)
                    .start()

                animatedStar2.animate()
                    .x(starView.x)
                    .y(starView.y)
                    .rotationBy(36000F)
                    .setDuration(600)
                    .start()


                animatedStar3.animate()
                    .x(starView.x)
                    .y(starView.y)
                    .rotationBy(36000F)
                    .setDuration(700)
                    .start()


                animatedStar4.animate()
                    .x(starView.x)
                    .y(starView.y)
                    .rotationBy(36000F)
                    .setDuration(800)
                    .start()

                animatedStar5.animate()
                    .x(starView.x)
                    .y(starView.y)
                    .rotationBy(36000F)
                    .setDuration(900)
                    .start()


            }

        }, 1000)


        resetTheviewAfterAnswer()
        handler.postDelayed(object : Runnable {
            override fun run() {
                Log.e("checkMedi", "startAndVisibleStarts2")
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

    fun play(soundName: String, looping: Boolean) {
        Log.e("checkMedi", "play")
        beatBox = BeatBox(this)
        mediaPlayer = MediaPlayer()
        for (item in beatBox.soundsList) {
            if (item.soundName.equals(soundName))
                beatBox.play(item, mediaPlayer, looping)
        }
    }

    fun playOtherSound(soundName: String, looping: Boolean) {
        Log.e("checkMedi", "playOtherSound")
        mediaPlayer2 = MediaPlayer()

        for (item in beatBox.soundsList) {
            if (item.soundName.equals(soundName))
                beatBox.play(item, mediaPlayer2!!, looping)
        }

    }

    fun goToResultActivity() {
        Log.e("checkMedi", "goToResultActivity")
        tasksAfterDestroying()
        val intent = ResultActivity.newIntent(
            this,
            correctAnswerCount,
            incorrectAnswerCount
        )
        startActivity(intent)
        finish()
    }

}