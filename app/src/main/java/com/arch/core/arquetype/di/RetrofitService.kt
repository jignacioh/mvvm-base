package com.arch.core.arquetype.di

import com.arch.core.arquetype.live_data.login.PojoLogin
import com.arch.core.arquetype.model.LoginLD
import com.arch.core.arquetype.model.Model
import com.arch.core.arquetype.model.Task
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @POST("/fakes/MyFakeResponse.php")
    fun getTasks(): Deferred<Response<Model>>

    @GET("/base_demo/chucknorris.php")
    fun getDataLogin() : Deferred<Response<LoginLD>>
}

