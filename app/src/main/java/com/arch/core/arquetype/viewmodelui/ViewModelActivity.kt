package com.arch.core.arquetype.viewmodelui

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.arch.core.arquetype.BaseDemoActivity
import com.arch.core.arquetype.BaseViewModel
import com.arch.core.arquetype.R
import com.arch.core.arquetype.databinding.ActivityViewModelBinding

class ViewModelActivity : BaseDemoActivity<ActivityViewModelBinding, BaseViewModel>() {

    override val layoutId: Int
        get() = R.layout.activity_view_model //TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val bindingVariable: Int
        get() = 1//TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val viewModel: BaseViewModel
        get() = getViewModel() //TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
    }

    fun getViewModel() : UiViewModel {
        val factory = ViewModelProvider.NewInstanceFactory()
        return ViewModelProviders.of(this, factory).get(UiViewModel::class.java)

    }
}
