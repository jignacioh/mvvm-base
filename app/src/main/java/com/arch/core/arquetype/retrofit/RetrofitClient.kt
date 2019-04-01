package com.arch.core.arquetype.retrofit

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    fun retrofitClient() : Retrofit{
        val  gson : Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofitClient
    }
}