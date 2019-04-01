package com.arch.core.arquetype.live_data.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arch.core.arquetype.retrofit.RestClient
import com.arch.core.arquetype.retrofit.RetrofitClient
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class LoginRepository{

    var statusMutable : MutableLiveData<Response<PojoLogin>>? = null

    init {
        statusMutable = MutableLiveData()
    }

    fun getMutable() : MutableLiveData<Response<PojoLogin>>{
        if (statusMutable == null)
            statusMutable =  MutableLiveData()

        return statusMutable as MutableLiveData<Response<PojoLogin>>
    }

    fun repositoryResponse(user : String, pass : String, observable: Observable<Response<PojoLogin>>) : Boolean{
        val success : Boolean
        var obs : Observable<Response<PojoLogin>>

        if (user.isNullOrEmpty() or pass.isNullOrEmpty()){
            success = false
        }
        else{
            val retrofitClient = RetrofitClient()

            val service = retrofitClient.retrofitClient().create(RestClient::class.java)

            val call = service.getDataLogin()

            call.enqueue(object : Callback<PojoLogin> {
                override fun onFailure(call: Call<PojoLogin>, t: Throwable) {
                    Log.e("Error Retrofit", t.message)
                }

                override fun onResponse(call: Call<PojoLogin>, response: Response<PojoLogin>) {
                    val responseRetrofit : PojoLogin = response.body()!!
                    Log.i("Response exitoso", "Fin Consulta...")

                    //observable = Observable.just(response)
                    //statusMutable?.postValue(response)
                }

            })

            success = true
        }

        return success
    }

    fun obtenerObserver(response: Response<PojoLogin>) : Observer<Response<PojoLogin>>{
        return object : Observer<Response<PojoLogin>> {
            override fun onComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSubscribe(d: Disposable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(t: Response<PojoLogin>) {
                statusMutable?.postValue(response)
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }
}