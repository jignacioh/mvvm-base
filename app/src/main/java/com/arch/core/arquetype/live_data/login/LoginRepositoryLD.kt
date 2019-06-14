package com.arch.core.arquetype.live_data.login

import com.arch.core.arquetype.base.BaseRepository
import com.arch.core.arquetype.di.Either
import com.arch.core.arquetype.di.GetLoginFailure
import com.arch.core.arquetype.di.RetrofitFactory
import com.arch.core.arquetype.model.LoginLD
import java.lang.Exception

class LoginRepositoryLD : BaseRepository() {

    suspend fun validarUser() : Either<GetLoginFailure, LoginLD>{
        try {
            val service = RetrofitFactory.makeRetrofitService()
            val loginResponse = safeApiCall(
                call = { service.getDataLogin().await() },
                errorMessage = "Error Al Loguearse"
            )
            return Either.Right(LoginLD(loginResponse!!.category, loginResponse!!.icon_url, loginResponse!!.id, loginResponse!!.url, loginResponse!!.value ))
        }
        catch(ex: Exception) {
            return Either.Left(GetLoginFailure.NetworkConnection())
        }
    }

}