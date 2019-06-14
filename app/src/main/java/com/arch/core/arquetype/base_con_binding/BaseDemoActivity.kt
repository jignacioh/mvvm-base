package com.arch.core.arquetype.base_con_binding

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import android.content.pm.PackageManager
import android.os.Build
import android.annotation.TargetApi
import android.app.Dialog
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog


abstract class BaseDemoActivity<T : ViewDataBinding, V : BaseViewModel<*, *>> : AppCompatActivity() {

    var pBar : ProgressBar? = null
    var dialog: Dialog? = null

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

    fun showLoader(){

        if (pBar == null)
            pBar = ProgressBar(this)
        pBar!!.setBackgroundResource(android.R.color.transparent)
        //pBar.setIndeterminateDrawableTiled(resources.getDrawable(android.R.drawable.ic_media_play))

        /* alertDialog = AlertDialog.Builder(this, android.R.style.notitlebar)
         alertDialog//.setTitle("Loading")
             .setCancelable(false)
             .setView(pBar)
             //.setMessage("Message")
             .show()
         */

        if (dialog == null){
            dialog = Dialog(this)
        }

        dialog!!.window.setBackgroundDrawableResource(android.R.color.transparent)
        dialog!!.setContentView(pBar)
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

    fun dismissLoader(){
        if (dialog != null)
            dialog?.dismiss()
    }

}



