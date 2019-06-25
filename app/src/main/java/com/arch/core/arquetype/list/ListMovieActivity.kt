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
import com.arch.core.arquetype.databinding.ActivityMoviesBinding
import com.arch.core.arquetype.model.Movie
import com.arch.core.arquetype.model.Task
import com.arch.core.arquetype.viewmodelui.TasksAdapter
import com.arch.core.arquetype.viewmodelui.TasksNavigator
import com.arch.core.arquetype.viewmodelui.TasksViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListMovieActivity : BaseActivity<ActivityMoviesBinding, ListMovieViewModel>(), ListMovieNavigator, SwipeRefreshLayout.OnRefreshListener {

    override val viewModel: ListMovieViewModel
        get() {
            val model: ListMovieViewModel by viewModel()
            return model
        }

    override val layoutId: Int
        get() = R.layout.activity_movies
    override val bindingVariable: Int
        get() = BR.moviesViewModel

    private var mActivityMoviesBinding: ActivityMoviesBinding? = null

    private val tasks = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMoviesBinding = viewDataBinding
        viewModel.setNavigator(this)

        mActivityMoviesBinding!!.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val rvAdapter = ListMovieAdapter(tasks)
        mActivityMoviesBinding!!.recyclerView.adapter = rvAdapter

    }

    override fun onResume() {
        super.onResume()
        //viewModel.tasksLiveData.observe(this,observerTask)

        viewModel.state.observe(this,observerTask2)
    }

    val observerTask2 = Observer<ListMovieViewModel.MovieState> { value ->

        onTasksStateChange(value)
    }

    override fun onRefresh() {
        viewModel.addTwo()
    }

    override fun showAction(state : Boolean) {

        Log.i("showAction", "doAction")
        mActivityMoviesBinding!!.swipeLayout.isRefreshing =state
    }

    override fun showError() {

    }
    override fun onDestroy() {
        super.onDestroy()
        viewModel.state.removeObserver(observerTask2)
    }

    private fun onTasksStateChange(state: ListMovieViewModel.MovieState?) {
        when (val tasksState = state!!) {
            is ListMovieViewModel.MovieState.None -> hideLoading()
            is ListMovieViewModel.MovieState.Loading -> showLoading()
            is ListMovieViewModel.MovieState.Empty -> showEmptyState()
            is ListMovieViewModel.MovieState.Success -> renderList(tasksState.tasks)
        }
    }

    private fun hideLoading() {
        Log.i("LOADING","LOADING")
        mActivityMoviesBinding!!.swipeLayout.isRefreshing =false
    }

    private fun showLoading() {
        Log.i("LOADING","LOADING")
        mActivityMoviesBinding!!.swipeLayout.isRefreshing =true
    }

    private fun showEmptyState() {
        Log.i("VACIO","VACIO")
        mActivityMoviesBinding!!.swipeLayout.isRefreshing =false
    }

    private fun renderList(allTasks: List<Movie>) {
        Log.i("RENDER LIST","RENDER LIST")
        mActivityMoviesBinding!!.swipeLayout.isRefreshing =false
        tasks.addAll(allTasks)
        mActivityMoviesBinding!!.recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onFragmentAttached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentDetached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

