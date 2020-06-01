package com.akaf.preway.model

import com.google.gson.annotations.SerializedName

data class VerifyMobile(

    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: User

)