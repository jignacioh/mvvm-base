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
data class LoginLD(var category : List<String>, var icon_url : String, var id : String, var url : String, var value : String){}