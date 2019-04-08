package com.arch.core.arquetype.live_data.login

import androidx.lifecycle.*
import com.arch.core.arquetype.base_con_binding.BaseViewModel
import com.arch.core.arquetype.coroutines.CoRoutine
import com.arch.core.arquetype.login.LoginModel
import com.arch.core.arquetype.viewmodelui.UINavigator
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class LoginViewModel(val repository : LoginRepository) : BaseViewModel<UINavigator, LoginModel>(){

    val mutableResponse = MutableLiveData<Response<PojoLogin>>()

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)


    override val loginModel: LoginModel
        get() = LoginModel()


    //val flagLogin = MutableLiveData<Boolean>()

   /* init {
        flagLogin.value = false
        //lifeCycleRegistry = LifecycleRegistry(this)
    }*/

    fun changeFlagLogin(){
        //flagLogin.value = true
        scope.launch {
            val responsePojo = repository.validarUser()
            if (responsePojo != null)
                mutableResponse.value = responsePojo
        }
    }

    fun repositoryResponse(user : String, pass : String){

        /*repository.statusMutable?.observe(this, object : Observer<Response<PojoLogin>> {
            override fun onChanged(t: Response<PojoLogin>?) {
                flagLogin.value = true
            }

        })*/

        //repository.repositoryResponse(user, pass)

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
