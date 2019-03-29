package com.arch.core.arquetype

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.arch.core.arquetype.base.BaseRepository
import com.arch.core.arquetype.base.BaseViewModel
import com.arch.core.arquetype.model.Model
import com.arch.core.arquetype.model.Task
import com.arch.core.arquetype.viewmodelui.RetrofitFactory
import com.arch.core.arquetype.viewmodelui.TasksNavigator
import kotlinx.coroutines.*
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

class Node {
}



class TasksViewModel (val repository : TasksRepository) : BaseViewModel<TasksNavigator>() {

    var arrayList = MutableLiveData<Model>()
    var arrayList2 = MutableLiveData<Model>()
    val job = Job()

    //val uiScope = CoroutineScope(Dispatchers.Main + job)

    var popularMoviesLiveData = MutableLiveData<MutableList<Task>>()

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)


    fun addOne() {

        //getNavigator()?.showAction()
        var repo : MutableLiveData<Model>?=null
        /*uiScope.launch {
            print(Thread.currentThread().name)
            val deferred: Deferred<MutableLiveData<Model>> = async(Dispatchers.IO){
                repository.getOneMore()
            }
           arrayList.postValue(deferred.await().value)

        }*/
        /*GlobalScope.launch {
             val response = withContext(Dispatchers.Main){
                 repository.getOneMore()
             }
             arrayList.postValue(response.value)
         }*/

        /*
        var myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = repository.getOneMore()

            withContext(Dispatchers.Main) {
                arrayList.postValue(result.value)
            }
        }
        myJob.start()
        */
        /*
        if (this.arrayList.hasObservers())
            this.arrayList.postValue(repository.getOneMore().value)
*/
        /*
        repository.getExtraTasks()
        repository.getExtraTasks()

        arrayList.postValue(repository.getExtraTasks().value)
        */

        scope.launch {
            val popularMovies = repository.getExTasks()
            if (popularMovies != null) {
                popularMoviesLiveData.postValue(popularMovies)
            }
        }


/*
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val request = service.getTasks()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                       // arrayList.postValue(response.body().parts)
                    } else {
                        Log.i("LIST","error")
                    }
                    Log.i("LIST","OK")
                } catch (e: HttpException) {
                    Log.e("LIST",e.message)
                } catch (e: Throwable) {
                    Log.e("LIST",e.message)
                }
            }
        }*/
    }

/*    fun successHandler(val model : Model): (Model?) -> Unit {
        return null
    }*/

    //override val coroutineContext: CoroutineContext =
    //    Dispatchers.Main + SupervisorJob()
    suspend fun loadDataFromUI() = withContext(Dispatchers.IO){
        // Switch to the IO dispatcher to perform blocking IO:
        val ioData = withContext(Dispatchers.Main) {
            repository.getOneMore()
        }
        arrayList.postValue(ioData.value) // use the data from IO to update UI in the main thread
    }

    interface TasksRepository {
        fun giveHello(): String
        fun getTasks() : MutableLiveData<Model>
        fun getOneMore() : MutableLiveData<Model>
        fun getExtraTasks() : MutableLiveData<Model>?
        fun getExtraTasks(successHandler: (Model?) -> Unit)
        suspend fun getExTasks() : MutableList<Task>?
    }

    open class TasksRepositoryImpl() : BaseRepository(),TasksRepository {

        override fun getExtraTasks(): MutableLiveData<Model>? {
            val arrayList = MutableLiveData<Model>()
            val service = RetrofitFactory.makeRetrofitService()

            CoroutineScope(Dispatchers.IO).launch {
                val request = service.getTasks()
                withContext(Dispatchers.Main) {
                    try {
                        val response = request.await()

                        if (response.isSuccessful) {
                            //arrayList.value=response.body()
                            //successHandler(response.body())
                            arrayList.postValue(response.body())
                        } else {
                            Log.i("LIST","error")
                        }
                        //callback.onResponse(null,response)
                        Log.i("LIST","OK")
                    } catch (e: HttpException) {
                        Log.e("LIST",e.message)
                    } catch (e: Throwable) {
                        Log.e("LIST",e.message)
                    }
                }
            }
            return arrayList
        }

        override fun getExtraTasks(successHandler: (Model?) -> Unit) {
            val arrayList = MutableLiveData<Model>()
            val service = RetrofitFactory.makeRetrofitService()

            CoroutineScope(Dispatchers.IO).launch {
                val request = service.getTasks()
                withContext(Dispatchers.Main) {
                    try {
                        val response = request.await()
                        if (response.isSuccessful) {
                            //arrayList.value=response.body()
                            //successHandler(response.body())
                        } else {
                            Log.i("LIST","error")
                        }
                        //callback.onResponse(null,response)
                        Log.i("LIST","OK")
                    } catch (e: HttpException) {
                        Log.e("LIST",e.message)
                    } catch (e: Throwable) {
                        Log.e("LIST",e.message)
                    }
                }
            }

        }

        override suspend fun getExTasks() : MutableList<Task>?{
            val service = RetrofitFactory.makeRetrofitService()
            //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
            val movieResponse = safeApiCall(
                call = {service.getTasks().await()},
                errorMessage = "Error Fetching Popular Movies"
            )

            return movieResponse?.parts!!.toMutableList()

        }


        override fun getOneMore(): MutableLiveData<Model> {
            Thread.sleep(5000)
            val mutableList = MutableLiveData<Model>()
            val model = Model(ArrayList<Task>())

            val dataList = ArrayList<Task>()
            dataList.add(Task("Google", 5))

            model.parts=dataList
            mutableList.value=model
            return mutableList
        }


        override fun getTasks(): MutableLiveData<Model> {

            val mutableList = MutableLiveData<Model>()
            val model = Model(ArrayList<Task>())

            val dataList = ArrayList<Task>()
            dataList.add(Task("Android", 1))
            dataList.add(Task("IOs", 2))
            dataList.add(Task("Windows", 3))
            dataList.add(Task("Native", 4))

            model.parts=dataList
            mutableList.value=model





            return mutableList
        }

        override fun giveHello() = "Hello Koin"

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init() {
        Log.i("onCreate","onCreate viewModel")
        arrayList = repository.getTasks()

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy(){
        Log.i("onDestroy","onDestroy viewModel")
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}