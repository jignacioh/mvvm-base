package com.arch.core.arquetype.repository

import com.arch.core.arquetype.di.Either
import com.arch.core.arquetype.di.GetTasksFailure
import com.arch.core.arquetype.model.Task

interface MoviesRepository {
    suspend fun getAllMovies(maxNumberOfTasks: Int): List<Task>
}