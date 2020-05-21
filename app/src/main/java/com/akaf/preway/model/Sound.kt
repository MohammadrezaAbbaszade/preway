package com.akaf.preway.model

import android.util.Log

class Sound {
    var soundName: String
    var assetPath: String
    var soundId: Int = 0

    constructor(assetPath: String) {

        this.assetPath = assetPath


        Log.e("SoundNmae", assetPath)
        var paths = assetPath.split("/")
        Log.e("SoundNmae", assetPath)
        var name = paths[paths.size - 1]


        soundName = name

        Log.e("SoundNmaeeee", soundName);
    }


}