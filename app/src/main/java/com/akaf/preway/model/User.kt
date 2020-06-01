package com.akaf.preway.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("credit")
    val credit: String,
    @SerializedName("coins")
    val coins: Long,
    @SerializedName("image")
    val image: String,

    @SerializedName("level")
    val level: String,

    @SerializedName("job")
    val job: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("present_code")
    val present_code: String,

    @SerializedName("grade")
    val grade: Int,

    @SerializedName("life")
    val life: Int,

    @SerializedName("xp")
    val xp: Int,

    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String
)