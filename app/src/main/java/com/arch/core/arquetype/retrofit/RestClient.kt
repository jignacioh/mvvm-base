package com.arch.core.arquetype.retrofit

import com.arch.core.arquetype.live_data.login.PojoLogin
import okhttp3.Call
import retrofit2.http.GET

interface RestClient {

    @GET("/jokes/random?category=dev")
    fun getDataLogin() : retrofit2.Call<PojoLogin>
}