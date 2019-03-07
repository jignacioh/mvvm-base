package com.arch.core.arquetype

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import android.content.pm.PackageManager
import android.os.Build
import android.annotation.TargetApi
import android.view.inputmethod.InputMethodManager


abstract class BaseDemoActivity<T : ViewDataBinding, V : BaseViewModel<*, *>> : AppCompatActivity() {

    var viewDataBinding: T? = null
        private set
    private var mViewModel: V? = null

    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int
    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = if (mViewModel == null) viewModel else mViewModel
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.executePendingBindings()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    /*fun hideKeyboard(){
        var view : View = this.currentFocus

        if (view!=null){
            var inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)
            if (inputMethodManager!=null){
                inputMethodManager.hi
            }
        }
    }*/

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

}



