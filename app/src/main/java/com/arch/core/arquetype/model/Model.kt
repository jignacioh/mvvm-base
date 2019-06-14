package com.arch.core.arquetype.model

import com.arch.core.arquetype.live_data.login.PojoLogin
import com.arch.core.arquetype.base.BaseRepository
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Model(var parts:List<Task>) :BaseRepository.Mappable<ModelRest> {
    override fun mapToResult(): BaseRepository.Result<ModelRest> = when {
        isValid() -> BaseRepository.Result.Success(ModelRest(parts))

        else -> BaseRepository.Result.Error(Exception("User body is invalid"))
    }

    private fun isValid() = parts.isNotEmpty()
    // var parts: List<Task>? = ArrayList<Task>()


}

@JsonClass(generateAdapter = true)
//data class LoginLD(var pojoObject:PojoLogin) : BaseRepository.Mappable<ModelRestLogin>{
data class LoginLD(var category : List<String>, var icon_url : String, var id : String, var url : String, var value : String) : BaseRepository.Mappable<ModelRestLogin>{

    override fun mapToResult(): BaseRepository.Result<ModelRestLogin> = when {
        isValid() -> BaseRepository.Result.Success(ModelRestLogin(category, icon_url, id, url, value))

        else -> BaseRepository.Result.Error(java.lang.Exception("Login Error"))
    }

    private fun isValid() = (category!=null && icon_url!=null && id!=null && url!=null && value!=null)
}