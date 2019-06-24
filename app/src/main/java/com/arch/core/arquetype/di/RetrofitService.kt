package com.arch.core.arquetype.di

import com.arch.core.arquetype.model.ListMovie
import com.arch.core.arquetype.model.Model
import com.arch.core.arquetype.model.Task
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @GET("movie/popular")
    abstract fun loadMovies(@Query("api_key") apiKey: String): Deferred<ListMovie>

    @POST("/Fakes3/MyFakesResponse.php")
    fun getTasks(): Deferred<Response<Model>>

}

