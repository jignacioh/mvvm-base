package com.arch.core.arquetype.repository

import com.arch.core.arquetype.model.Task

interface TasksRepository {
    fun getTasks() : MutableList<Task>?
    suspend fun getMoreTasks() : MutableList<Task>?
}