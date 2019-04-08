package com.arch.core.arquetype.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.arch.core.arquetype.BR
import com.arch.core.arquetype.base_con_binding.BaseDemoActivity
import com.arch.core.arquetype.R
import com.arch.core.arquetype.databinding.LoginActivityBinding
import com.arch.core.arquetype.viewmodelui.UINavigator
import com.arch.core.arquetype.viewmodelui.ViewModelActivity
import kotlinx.android.synthetic.main.login_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseDemoActivity<LoginActivityBinding, LoginViewModel>(), UINavigator, View.OnClickListener {
    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val  koinViewModel : LoginViewModel by viewModel()

    val factory = ViewModelProvider.NewInstanceFactory()
    private var mLoginBinding: LoginActivityBinding? = null

    override val layoutId: Int
        get() = R.layout.login_activity
    override val bindingVariable: Int
        get() = BR.loginVM
    override val viewModel: LoginViewModel
        get() = koinViewModel//ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.login_activity)

        mLoginBinding = viewDataBinding
        viewModel.setNavigatorActivity(this)

        btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btnLogin){
            val user = txtUsername.text.toString()
            val pass = txtPass.text.toString()
            viewModel.validarUsuario(user, pass)
        }
    }

    override fun showAction() {

    }

    override fun successLogin() {
        startActivity(Intent(this, ViewModelActivity::class.java))
    }

    override fun campoVacio() {
        Toast.makeText(this, "Existen campos vacios...", Toast.LENGTH_LONG).show()
    }

}
