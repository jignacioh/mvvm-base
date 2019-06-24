package com.arch.core.arquetype.repository

import com.arch.core.arquetype.base.BaseRepository
import com.arch.core.arquetype.di.Either
import com.arch.core.arquetype.di.GetTasksFailure
import com.arch.core.arquetype.di.RetrofitFactory
import com.arch.core.arquetype.model.ListMovie
import com.arch.core.arquetype.model.Task

class MoviesRepositoryImpl() : BaseRepository(),MoviesRepository {


    override suspend fun getAllMovies(maxNumberOfTasks: Int): ListMovie {
        val service = RetrofitFactory.makeRetrofitService()
        val response = safeApiCall(
            call = {service.getTasks().await()},
            errorMessage = "Error Fetching"
        )
        return response!!.parts
    }

}