package com.arch.core.arquetype.viewmodelui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.arch.core.arquetype.BR
import com.arch.core.arquetype.R
import com.arch.core.arquetype.base.BaseActivity
import com.arch.core.arquetype.databinding.ActivityViewModelBinding
import org.koin.android.viewmodel.ext.android.viewModel


class ViewModelActivity : BaseActivity<ActivityViewModelBinding, UiViewModel>(), UINavigator {
    override fun campoVacio() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun successLogin() {

    }

    val factory = ViewModelProvider.NewInstanceFactory()


    override val viewModel: UiViewModel
        get() {
            // ViewModelProviders.of(this, factory).get(UiViewModel::class.java)
            val model: UiViewModel by viewModel()
            return model
        }
    override val layoutId: Int
        get() = R.layout.activity_view_model
    override val bindingVariable: Int
        get() = BR.viewModel

    private var mActivityLoginBinding: ActivityViewModelBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLoginBinding = viewDataBinding
        viewModel.setNavigator(this)

        var user = User()
        user.name = "Juan"

        viewDataBinding?.user = user

    }

    override fun showAction() {
        Log.i("showAction", "doAction" + viewModel.repo.giveHello())
    }

}
