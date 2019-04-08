package com.arch.core.arquetype.viewmodelui

import  android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arch.core.arquetype.BR
import com.arch.core.arquetype.base.BaseActivity
import com.arch.core.arquetype.R


import com.arch.core.arquetype.databinding.ActivityTasksBinding
import com.arch.core.arquetype.model.Task
import org.koin.android.viewmodel.ext.android.viewModel

class TasksActivity : BaseActivity<ActivityTasksBinding, TasksViewModel>(),TasksNavigator,SwipeRefreshLayout.OnRefreshListener {

    override val viewModel: TasksViewModel
        get() {
            val model: TasksViewModel by viewModel()
            return model
        }

    override val layoutId: Int
        get() = R.layout.activity_tasks
    override val bindingVariable: Int
        get() = BR.tasksViewModel

    private var mActivityTasksBinding: ActivityTasksBinding? = null

    private val tasks = ArrayList<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityTasksBinding = viewDataBinding
        viewModel.setNavigator(this)

        mActivityTasksBinding!!.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val rvAdapter = TasksAdapter(tasks)
        mActivityTasksBinding!!.recyclerView.adapter = rvAdapter

        //lifecycle.addObserver(viewModel)
        Log.i("onCreate","onCreate activity")

    }

    override fun onResume() {
        super.onResume()
        Log.i("onResume","onResume activity")
        viewModel.tasksLiveData.observe(this,observerTask)
    }

    val observerTask = Observer<MutableList<Task>> { value ->
        tasks.addAll(value)
        mActivityTasksBinding!!.recyclerView.adapter!!.notifyDataSetChanged()
    }


    override fun onRefresh() {
       viewModel.addOne()
    }

    override fun showAction(state : Boolean) {

        Log.i("showAction", "doAction")
        mActivityTasksBinding!!.swipeLayout!!.isRefreshing =state
    }

    override fun showError() {

    }
    override fun onDestroy() {
        super.onDestroy()
        viewModel.tasksLiveData.removeObserver(observerTask)
    }
}