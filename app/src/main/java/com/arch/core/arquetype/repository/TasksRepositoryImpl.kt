package com.arch.core.arquetype.repository

import com.arch.core.arquetype.base.BaseRepository
import com.arch.core.arquetype.di.RetrofitFactory
import com.arch.core.arquetype.model.Task
import com.arch.core.arquetype.di.Either
import com.arch.core.arquetype.di.GetTasksFailure

open class TasksRepositoryImpl() : BaseRepository(),TasksRepository {

    override suspend fun getAllTasks(): Either<GetTasksFailure, MutableList<Task>> {

        try {
            val service = RetrofitFactory.makeRetrofitService()
            val response = safeApiCall(
                call = {service.getTasks().await()},
                errorMessage = "Error Fetching"
            )
            return Either.Right(response!!.parts.toMutableList())
        } catch (ex: Exception) {
            return Either.Left(GetTasksFailure.NetworkConnection())
        }

    }

    override suspend fun getMoreTasks() : MutableList<Task>?{
        val service = RetrofitFactory.makeRetrofitService()
        val movieResponse = safeApiCall(
            call = {service.getTasks().await()},
            errorMessage = "Error Fetching"
        )

        return movieResponse?.parts!!.toMutableList()

    }

    override fun getTasks(): MutableList<Task>? {
        val dataList = ArrayList<Task>()
        dataList.add(Task( 1,"Android"))
        dataList.add(Task( 2,"IOs"))
        dataList.add(Task( 3,"Windows"))
        dataList.add(Task( 4,"Native"))

        object : TasksRepository {
            override fun getTasks(): MutableList<Task>? {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override suspend fun getMoreTasks(): MutableList<Task>? {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        return dataList.toMutableList()
    }
}