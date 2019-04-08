package com.arch.core.arquetype.repository

import com.arch.core.arquetype.base.BaseRepository
import com.arch.core.arquetype.di.RetrofitFactory
import com.arch.core.arquetype.model.Task

open class TasksRepositoryImpl() : BaseRepository(),TasksRepository {

    override suspend fun getMoreTasks() : MutableList<Task>?{
        val service = RetrofitFactory.makeRetrofitService()
        val movieResponse = safeApiCall(
            call = {service.getTasks().await()},
            errorMessage = "Error Fetching Popular Movies"
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