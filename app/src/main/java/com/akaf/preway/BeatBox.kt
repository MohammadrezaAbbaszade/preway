package com.akaf.preway

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import android.util.Log
import java.io.IOException

class BeatBox {
    lateinit var afd: AssetFileDescriptor
    var context: Context

    lateinit var assetManager: AssetManager
    var soundsList: ArrayList<Sound>


    companion object {

        val ASSET_DIR_SAMPLE_SOUNDS = "sample_sounds"
        val TAG = "BeatBox"
        val MAX_STREAMS = 5
    }

    constructor(context: Context) {

        this.context = context

        soundsList = ArrayList<Sound>()



        loadSounds()
    }


    private fun loadSounds() {
        assetManager = context.assets

        try {
            var soundsName = assetManager.list(ASSET_DIR_SAMPLE_SOUNDS)
            Log.e("SoundNmae", soundsName!!.size.toString())
            for (i in 1 until ((soundsName!!.size - 1))) {
                var asserPath = ASSET_DIR_SAMPLE_SOUNDS + "/" + soundsName[i]

                var sound = Sound(asserPath)

                load(sound)

                soundsList.add(sound)
            }


        } catch (e: IOException) {

        }

    }

    private fun load(sound: Sound) {


    }


    fun play(sound: Sound,mediaPlayer: MediaPlayer,looping:Boolean) {

        Log.e("playedSS", "played" + sound.soundName)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mediaPlayer.setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
        } else {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        }
        try {
            afd = assetManager.openFd(sound.assetPath)
            mediaPlayer.setDataSource(
                afd.getFileDescriptor(),
                afd.getStartOffset(),
                afd.getLength()
            )
            mediaPlayer.isLooping=looping
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
                override fun onPrepared(mp: MediaPlayer?) {
                    mp?.start()
                }
            })

        } catch (e: IOException) {
            Log.e("errr", e.message)
        }
    }

fun release(mediaPlayer: MediaPlayer){
    mediaPlayer.release()
}
}