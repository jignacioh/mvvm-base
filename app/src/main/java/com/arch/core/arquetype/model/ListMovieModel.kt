package com.arch.core.arquetype.model

import com.arch.core.arquetype.base.BaseRepository
import com.arch.core.arquetype.model.rest.ListMovieModelRest
import com.arch.core.arquetype.model.rest.ModelRest
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListMovieModel(var parts:ListMovie) : BaseRepository.Mappable<ListMovieModelRest> {
    override fun mapToResult(): BaseRepository.Result<ListMovieModelRest> = when {
        isValid() -> BaseRepository.Result.Success(ListMovieModelRest(parts))

        else -> BaseRepository.Result.Error(Exception("User body is invalid"))
    }

    private fun isValid() = parts.results!!.isNotEmpty()
    // var parts: List<Task>? = ArrayList<Task>()


}