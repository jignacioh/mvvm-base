package com.arch.core.arquetype.live_data.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arch.core.arquetype.base.BaseRepository
import com.arch.core.arquetype.di.RetrofitFactory
import com.arch.core.arquetype.model.LoginLD
import com.arch.core.arquetype.retrofit.RestClient
import com.arch.core.arquetype.retrofit.RetrofitClient
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class LoginRepositoryLD : BaseRepository() {

    suspend fun validarUser() : LoginLD?{
        val service = RetrofitFactory.makeRetrofitService()
        val loginResponse = safeApiCall(
            call = {
                service.getDataLogin().await()
            },
            errorMessage = "Error Al Loguearse"
        )


        return loginResponse
    }

}