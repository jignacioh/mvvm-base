package com.arch.core.arquetype.usecase

import com.arch.core.arquetype.di.Either
import com.arch.core.arquetype.di.GetTasksFailure
import com.arch.core.arquetype.executor.CoroutinesExecutor
import com.arch.core.arquetype.model.ListMovie
import com.arch.core.arquetype.model.Task
import com.arch.core.arquetype.repository.MoviesRepositoryImpl
import com.arch.core.arquetype.repository.TasksRepositoryImpl
import com.arch.core.arquetype.repository.network.Failure

    class MoviesUseCase (val getMoviesRepositoryImpl: MoviesRepositoryImpl, val getCoroutinesExecutor: CoroutinesExecutor) :UseCase<ListMovie,MoviesUseCase.Params>(getCoroutinesExecutor){

    data class Params(val maxNumberOfTasks: Int)

    data class GetFriendsFailure(val error: Exception) : Failure.FeatureFailure(error)

    override suspend fun run(params: Params): Either<Failure, ListMovie> {
        return try {
            val friends = getMoviesRepositoryImpl.getAllMovies(params.maxNumberOfTasks)

            Either.Right(friends)
        } catch (exp: Exception) {
            Either.Left(GetFriendsFailure(exp))
        }
    }
}
