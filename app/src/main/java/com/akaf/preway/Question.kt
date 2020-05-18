package com.akaf.preway

import android.content.Context

class Question(val context: Context) {

    lateinit var questions: ArrayList<String>
    lateinit var answers: ArrayList<String>


    init {
        questions = ArrayList<String>()
        answers = ArrayList<String>()

        questions.add(context.resources.getString(R.string.one))
        questions.add(context.resources.getString(R.string.Two))
        questions.add(context.resources.getString(R.string.Three))
        questions.add(context.resources.getString(R.string.Four))
        questions.add(context.resources.getString(R.string.Five))
        questions.add(context.resources.getString(R.string.Six))
        questions.add(context.resources.getString(R.string.Seven))
        questions.add(context.resources.getString(R.string.Eight))
        questions.add(context.resources.getString(R.string.Nine))
        questions.add(context.resources.getString(R.string.Ten))
        questions.add(context.resources.getString(R.string.Eleven))
        questions.add(context.resources.getString(R.string.Twelve))


        answers.add(context.resources.getString(R.string.AoneOne))
        answers.add(context.resources.getString(R.string.ATwoThree))
        answers.add(context.resources.getString(R.string.AThreeOne))
        answers.add(context.resources.getString(R.string.AThreeTwo))
        answers.add(context.resources.getString(R.string.AThreeThree))
        answers.add(context.resources.getString(R.string.AThreeThree))
        answers.add(context.resources.getString(R.string.AThreeOne))
        answers.add(context.resources.getString(R.string.AThreeTwo))
        answers.add(context.resources.getString(R.string.AThreeTwo))
        answers.add(context.resources.getString(R.string.AThreeOne))
        answers.add(context.resources.getString(R.string.AThreeThree))
        answers.add(context.resources.getString(R.string.AThreeThree))

    }


}