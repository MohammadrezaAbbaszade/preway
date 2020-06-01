package com.akaf.preway

import android.content.Context
import android.content.SharedPreferences

class SharePreferenceData {

    companion object {
        private val PREF_NAME = "preway"
        private val IS_PLAYED = "isPlayed"
        private val QUESTION_NUMBER = "0"
        private val TOKEN="token"
        private fun getSharePreference(context: Context): SharedPreferences {

            return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        }

        public fun setPlayerResult(context: Context, check: Boolean) {

            val pref = getSharePreference(context)
            val editor = pref.edit()
            editor.putBoolean(IS_PLAYED, check)
            editor.apply()
        }

        public fun getPlayerResult(context: Context):Boolean? {

            val pref:SharedPreferences?=getSharePreference(context)

            return pref?.getBoolean(IS_PLAYED,false)

        }

        public fun setQuestionCounter(context: Context, number: Int) {

            val pref = getSharePreference(context)
            val editor = pref.edit()
            editor.putInt(QUESTION_NUMBER, number)
            editor.apply()
        }

        public fun getQuestionCounter(context: Context):Int? {

            val pref:SharedPreferences?=getSharePreference(context)

            return pref?.getInt(QUESTION_NUMBER,0)

        }

        public fun setToken(context: Context, token:String) {

            val pref = getSharePreference(context)
            val editor = pref.edit()
            editor.putString(TOKEN, token)
            editor.apply()
        }

        public fun getToken(context: Context):String? {

            val pref:SharedPreferences?=getSharePreference(context)

            return pref?.getString(TOKEN,null)

        }
    }





}