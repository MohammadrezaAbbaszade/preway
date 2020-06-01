package com.akaf.preway.service

import com.akaf.preway.model.Login
import com.akaf.preway.model.User
import com.akaf.preway.model.VerifyMobile
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @POST("user/signin")
    fun singning(@Body login: Login):Call<Login>


    @POST("user/verify")
    fun verifyMobile(@Body login: Login):Call<VerifyMobile>


    @POST("user/fill-info")
    fun fillInfo(@Header("Authorization")token:String,@Body login: Login):Call<Login>

    @GET("user/check-info")
    fun checkInfo(@Header("Authorization")token:String):Call<Login>
}