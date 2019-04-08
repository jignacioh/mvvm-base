package com.arch.core.arquetype.usecase

import com.arch.core.arquetype.di.Either
import com.arch.core.arquetype.di.GetTasksFailure
import com.arch.core.arquetype.executor.CoroutinesExecutor
import com.arch.core.arquetype.model.Task
import com.arch.core.arquetype.repository.TasksRepositoryImpl

open class TasksUseCaseImpl (val getTasksRepositoryImpl: TasksRepositoryImpl,val getCoroutinesExecutor: CoroutinesExecutor) :UseCase(getCoroutinesExecutor){

    private var onTasksLoad:(List<Task>) -> Unit = {}
    private var onConnectionError: () -> Unit = {}

    fun execute(onResult: (Either<GetTasksFailure, MutableList<Task>>) -> Unit) {
        this.onTasksLoad = onTasksLoad
        this.onConnectionError = onConnectionError

        asyncExecute {
            val tasksResult = getTasksRepositoryImpl.getAllTasks()

            uiExecute {onResult(tasksResult)}
        }
    }



}
