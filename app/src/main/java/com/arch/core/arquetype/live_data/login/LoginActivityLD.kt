package com.arch.core.arquetype.live_data.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.arch.core.arquetype.BR
import com.arch.core.arquetype.base_con_binding.BaseDemoActivity
import com.arch.core.arquetype.R
import com.arch.core.arquetype.databinding.ActivityNameBinding
import com.arch.core.arquetype.koin.MyViewModel
import com.arch.core.arquetype.viewmodelui.UINavigator
import com.arch.core.arquetype.viewmodelui.ViewModelActivity
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import retrofit2.Response

class LoginActivity : BaseDemoActivity<ActivityNameBinding, LoginViewModel>(), UINavigator {

    val myViewModel : LoginViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.activity_name
    override val bindingVariable: Int
        get() = BR.loginLiveData
    override val viewModel: LoginViewModel
        get() = myViewModel //ViewModelProviders.of(this).get(LoginViewModel::class.java)

    lateinit var btnRunner : Button
    lateinit var txtUser : EditText
    lateinit var txtPassword : EditText
    lateinit var backgroundColor : RelativeLayout

    override fun showAction() {

    }

    override fun successLogin() {
        abrirIntent()
    }

    override fun campoVacio() {
        showError("Datos incorrectos")
    }

    private lateinit var viewmodel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_name)

        viewmodel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewmodel.setNavigatorActivity(this)

        btnRunner = findViewById(R.id.btnRunner)
        txtUser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)
        backgroundColor = findViewById(R.id.backgroundColor)


        viewmodel.mutableResponse.observe(this, object : Observer<Response<PojoLogin>> {
            override fun onChanged(response: Response<PojoLogin>?) {
                if (response != null) {
                    viewmodel.repositoryResponse(txtUser.text.toString(), txtPassword.text.toString())
                }
            }
        })

        validatePass()
/*
        btnRunner.setOnClickListener {
            Log.i("Android", "Inicio Consulta...")
            viewmodel.changeButtonText()
        }*/
    }

    fun abrirIntent(){
        backgroundColor.visibility = View.GONE
        startActivity(Intent(this, ViewModelActivity::class.java))
    }

    fun showError(message : String){
        backgroundColor.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun validatePass(){
        txtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 4) {
                    backgroundColor.visibility = View.VISIBLE
                    viewmodel.changeFlagLogin()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }
}
