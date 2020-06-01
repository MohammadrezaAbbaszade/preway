package com.akaf.preway.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("is_first")
    val is_first: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("mobile")
    val mobile:String,
    @SerializedName("auth_code")
    val auth_code:String,
    @SerializedName("username")
    val username:String,
    @SerializedName("presenter_code")
    val presenter_code:String,
    @SerializedName("error")
    val error:String

)