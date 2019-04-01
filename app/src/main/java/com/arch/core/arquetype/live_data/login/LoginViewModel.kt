package com.arch.core.arquetype.live_data.login

import androidx.lifecycle.*
import com.arch.core.arquetype.base_con_binding.BaseViewModel
import com.arch.core.arquetype.login.LoginModel
import com.arch.core.arquetype.viewmodelui.UINavigator
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Response

class LoginViewModel : BaseViewModel<UINavigator, LoginModel>(){

    lateinit var observable : Observable<Response<PojoLogin>>
    lateinit var observer : Observer<Response<PojoLogin>>


    override val loginModel: LoginModel
        get() = LoginModel()


    val flagLogin = MutableLiveData<Boolean>()
    val repository = LoginRepository()
/*
    init {
        flagLogin.value = false
        lifeCycleRegistry = LifecycleRegistry(this)
    }
*/
    fun changeFlagLogin(){
        flagLogin.value = true
    }

    fun repositoryResponse(user : String, pass : String){

        observable = object : Observable<Response<PojoLogin>>() {
            override fun subscribeActual(observer: Observer<in Response<PojoLogin>>?) {

            }
        }

        observer = object : Observer<Response<PojoLogin>> {
            override fun onComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSubscribe(d: Disposable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(t: Response<PojoLogin>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        observable.subscribe(observer)



        /*repository.statusMutable?.observe(this, object : Observer<Response<PojoLogin>> {
            override fun onChanged(t: Response<PojoLogin>?) {
                flagLogin.value = true
            }

        })*/


        repository.repositoryResponse(user, pass, observable)
        /*val success : Boolean

        if (user.isNullOrEmpty() or pass.isNullOrEmpty()){
            success = false
        }
        else{
            val retrofitClient = RetrofitClient()

            val service = retrofitClient.retrofitClient().create(RestClient::class.java)

            val call = service.getDataLogin()

            call.enqueue(object :Callback<PojoLogin>{
                override fun onFailure(call: Call<PojoLogin>, t: Throwable) {
                    Log.e("Error Retrofit", t.message)
                }

                override fun onResponse(call: Call<PojoLogin>, response: Response<PojoLogin>) {
                    val responseRetrofit : PojoLogin = response.body()!!
                    Log.i("Response exitoso", "Fin Consulta...")
                    getNavigatorActivity()?.successLogin()
                }

            })

            success = true
        }

        return success*/
    }
}

private fun <T> io.reactivex.Observer<T>.onNext() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
