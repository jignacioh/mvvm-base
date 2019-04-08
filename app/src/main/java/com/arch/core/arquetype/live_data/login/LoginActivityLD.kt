package com.arch.core.arquetype.live_data.login

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import com.arch.core.arquetype.BR
import com.arch.core.arquetype.base_con_binding.BaseDemoActivity
import com.arch.core.arquetype.R
import com.arch.core.arquetype.databinding.ActivityNameBinding
import com.arch.core.arquetype.viewmodelui.UINavigator
import com.arch.core.arquetype.viewmodelui.ViewModelActivity
import kotlinx.android.synthetic.main.activity_name.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivityLD : BaseDemoActivity<ActivityNameBinding, LoginViewModelLD>(), UINavigator {

    val myViewModel : LoginViewModelLD by viewModel()

    override val layoutId: Int
        get() = R.layout.activity_name
    override val bindingVariable: Int
        get() = BR.loginLiveData
    override val viewModel: LoginViewModelLD
        get() = myViewModel //ViewModelProviders.of(this).get(LoginViewModel::class.java)

    lateinit var btnRunner : Button
    lateinit var txtUser : EditText
    lateinit var txtPassword : EditText
    lateinit var backgroundLoader : RelativeLayout

    lateinit var animationDrawable: AnimationDrawable

    override fun showAction() {

    }

    override fun successLogin() {
        abrirIntent()
    }

    override fun campoVacio() {
        showError("Datos incorrectos")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_name)

        myViewModel.setNavigatorActivity(this)

        btnRunner = findViewById(R.id.btnRunner)
        txtUser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)
        backgroundLoader = findViewById(R.id.backgroundLoader)

        backgroundLoader.setOnClickListener {
            backgroundLoader.visibility = View.GONE
            hideLoader()
            myViewModel.cancelCoRoutine()
        }

        validatePass()
/*
        btnRunner.setOnClickListener {
            Log.i("Android", "Inicio Consulta...")
            myViewModel.changeButtonText()
        }*/
    }

    fun abrirIntent(){
        backgroundLoader.visibility = View.GONE
        startActivity(Intent(this, ViewModelActivity::class.java))
    }

    override fun showError(message : String){
        backgroundLoader.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun validatePass(){
        txtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 4) {
                    backgroundLoader.visibility = View.VISIBLE
                    showLoader()
                    myViewModel.changeFlagLogin()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    fun showLoader(){
        animationDrawable = ivLoader.drawable as AnimationDrawable
        ivLoader.post(Runnable { animationDrawable.start() })
    }

    fun hideLoader(){
        ivLoader.post(Runnable { animationDrawable.stop() })
    }
}
