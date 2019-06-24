package com.arch.core.arquetype.usecase

import com.arch.core.arquetype.di.Either
import com.arch.core.arquetype.di.GetTasksFailure
import com.arch.core.arquetype.executor.CoroutinesExecutor
import com.arch.core.arquetype.model.Task
import com.arch.core.arquetype.repository.TasksRepositoryImpl
import com.arch.core.arquetype.repository.network.Failure

open class TasksUseCaseImpl (val getTasksRepositoryImpl: TasksRepositoryImpl,val getCoroutinesExecutor: CoroutinesExecutor) :UseCase<List<Task>,TasksUseCaseImpl.Params>(getCoroutinesExecutor){

    private var onTasksLoad:(List<Task>) -> Unit = {}
    private var onConnectionError: () -> Unit = {}

    fun execute(onResult: (Either<GetTasksFailure, MutableList<Task>>) -> Unit) {
        asyncExecute {
            val tasksResult = getTasksRepositoryImpl.getAllTasks()

            uiExecute {
                onResult(tasksResult)
            }
        }
    }

    data class Params(val maxNumberOfTasks: Int)

    data class GetFriendsFailure(val error: Exception) : Failure.FeatureFailure(error)

    override suspend fun run(params: Params): Either<Failure, List<Task>> {
        return try {
            val friends = getTasksRepositoryImpl.getAllTasks2(params.maxNumberOfTasks)

            Either.Right(friends)
        } catch (exp: Exception) {
            Either.Left(GetFriendsFailure(exp))
        }
    }
}
