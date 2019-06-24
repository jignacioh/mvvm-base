package com.arch.core.arquetype.list

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arch.core.arquetype.BR
import com.arch.core.arquetype.R
import com.arch.core.arquetype.base.BaseActivity
import com.arch.core.arquetype.databinding.ActivityTasksBinding
import com.arch.core.arquetype.model.Task
import com.arch.core.arquetype.viewmodelui.TasksAdapter
import com.arch.core.arquetype.viewmodelui.TasksNavigator
import com.arch.core.arquetype.viewmodelui.TasksViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListMovieActivity : BaseActivity<ActivityTasksBinding, TasksViewModel>(), TasksNavigator, SwipeRefreshLayout.OnRefreshListener {

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

    }

    override fun onResume() {
        super.onResume()
        //viewModel.tasksLiveData.observe(this,observerTask)

        viewModel.state.observe(this,observerTask2)
    }

    val observerTask2 = Observer<TasksViewModel.TasksState> { value ->

        onTasksStateChange(value)
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

    private fun onTasksStateChange(state: TasksViewModel.TasksState?) {
        when (val tasksState = state!!) {
            is TasksViewModel.TasksState.Loading -> showLoading()
            is TasksViewModel.TasksState.Empty -> showEmptyState()
            is TasksViewModel.TasksState.Success -> renderList(tasksState.tasks)
        }
    }

    private fun showLoading() {
        Log.i("LOADING","LOADING")
        mActivityTasksBinding!!.swipeLayout!!.isRefreshing =true
    }

    private fun showEmptyState() {
        Log.i("VACIO","VACIO")
        mActivityTasksBinding!!.swipeLayout!!.isRefreshing =false
    }

    private fun renderList(allTasks: List<Task>) {
        Log.i("RENDER LIST","RENDER LIST")
        tasks.addAll(allTasks)
        mActivityTasksBinding!!.recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onFragmentAttached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentDetached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

