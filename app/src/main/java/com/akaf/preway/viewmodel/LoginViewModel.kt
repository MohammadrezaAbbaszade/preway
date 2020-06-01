package com.akaf.preway.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.akaf.preway.SharePreferenceData
import com.akaf.preway.model.Login
import com.akaf.preway.model.User
import com.akaf.preway.model.VerifyMobile
import com.akaf.preway.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var mVerifyMobile: MutableLiveData<VerifyMobile>
    lateinit var mLogin:MutableLiveData<Login>
    lateinit var mCheckInfo:MutableLiveData<Login>
    val mApi by lazy {
        RetrofitInstance.create()
    }




    fun singIn(login: Login):MutableLiveData<Login>{
       mLogin = MutableLiveData()
        mApi.singning(login).enqueue(object : Callback<Login> {
            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                if (response.isSuccessful) {
                    mLogin.setValue(response.body()!!)
                } else {
                    mLogin.setValue(null)
                    call.cancel()
                }
            }

            override fun onFailure(call: Call<Login>, t: Throwable) {
                mLogin.setValue(null)
                call.cancel()
            }

        })
        return mLogin
    }

    fun verifyMobile(login: Login):MutableLiveData<VerifyMobile>{
       mVerifyMobile = MutableLiveData()
        mApi.verifyMobile(login).enqueue(object : Callback<VerifyMobile> {
            override fun onResponse(call: Call<VerifyMobile>, response: Response<VerifyMobile>) {
                if (response.isSuccessful) {
                  mVerifyMobile.setValue(response.body()!!)
                } else {
                   mVerifyMobile.setValue(null)
                    call.cancel()
                }
            }

            override fun onFailure(call: Call<VerifyMobile>, t: Throwable) {
               mVerifyMobile.setValue(null)
                call.cancel()
            }

        })
        return mVerifyMobile
    }

    fun filInfo(token:String,login: Login): MutableLiveData<Login>{
        mLogin = MutableLiveData()
        mApi.fillInfo(token, login).enqueue(object : Callback<Login> {

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                if (response.isSuccessful) {
                    mLogin.setValue(response.body()!!)
                } else {
                    mLogin.setValue(null)
                    call.cancel()
                }
            }

            override fun onFailure(call: Call<Login>, t: Throwable) {
                mLogin.setValue(null)
                call.cancel()
            }
        })
        return mLogin
    }

    fun checkInfo(token:String):MutableLiveData<Login>{
     mCheckInfo = MutableLiveData()
      mApi.checkInfo(token).enqueue(object : Callback<Login> {

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                if (response.isSuccessful) {
                    mCheckInfo?.setValue(response.body()!!)
                } else {
                    mCheckInfo?.setValue(null)
                    call.cancel()
                }
            }

            override fun onFailure(call: Call<Login>, t: Throwable) {
                mCheckInfo?.setValue(null)
                call.cancel()
            }
        })
        return mCheckInfo
    }
}