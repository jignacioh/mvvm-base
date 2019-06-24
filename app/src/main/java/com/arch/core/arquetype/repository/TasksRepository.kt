package com.arch.core.arquetype.repository

import com.arch.core.arquetype.model.Task
import com.arch.core.arquetype.di.Either
import com.arch.core.arquetype.di.GetTasksFailure
import com.arch.core.arquetype.repository.network.Failure

interface TasksRepository {
    fun getTasks() : MutableList<Task>?
    suspend fun getMoreTasks() : MutableList<Task>?
    suspend fun getAllTasks(): Either<GetTasksFailure, MutableList<Task>>
    suspend fun getAllTasks2(maxNumberOfTasks: Int): List<Task>
}