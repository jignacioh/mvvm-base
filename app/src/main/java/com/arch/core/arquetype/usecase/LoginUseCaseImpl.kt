package com.arch.core.arquetype.usecase

import com.arch.core.arquetype.di.Either
import com.arch.core.arquetype.di.GetLoginFailure
import com.arch.core.arquetype.di.GetTasksFailure
import com.arch.core.arquetype.executor.CoroutinesExecutor
import com.arch.core.arquetype.live_data.login.LoginRepositoryLD
import com.arch.core.arquetype.live_data.login.PojoLogin
import com.arch.core.arquetype.model.LoginLD
import retrofit2.Response

open class LoginUseCaseImpl(val repositoryLD: LoginRepositoryLD, val getcoroutinesExecutor : CoroutinesExecutor) : UseCase(getcoroutinesExecutor) {

    private var onConnectionSuccess : (LoginRepositoryLD) -> Unit = {}
    private var onConnectionError : () -> Unit = {}

    fun execute(onResult: (Either<GetLoginFailure, LoginLD>) -> Unit) {
        this.onConnectionSuccess = onConnectionSuccess
        this.onConnectionError = onConnectionError

        asyncExecute {
            val loginResult = repositoryLD.validarUser()

            uiExecute {

                onResult(loginResult )
            }
        }
    }
}