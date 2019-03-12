package com.arch.core.arquetype.koin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.arch.core.arquetype.R
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception

class Main2Activity : AppCompatActivity() {

    val firtPresenter : MySimplePresenter by inject()
    val myViewModel : MyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        try {
            Log.i("KOIN INJECT", firtPresenter.sayHello())
            Log.i("KOIN LOGIN VIEWMODEL", myViewModel?.sayHello())
        }
        catch (e : Exception){
            Log.e("Error", e.message)
        }
    }
}
