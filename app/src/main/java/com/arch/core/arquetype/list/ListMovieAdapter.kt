package com.arch.core.arquetype.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arch.core.arquetype.R
import com.arch.core.arquetype.model.Movie
import com.arch.core.arquetype.model.Task

class ListMovieAdapter(val movieList: ArrayList<Movie>) : RecyclerView.Adapter<ListMovieAdapter.ViewHolder>() {



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.adapter_item_list, p0, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return movieList.size
    }
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.name.text = movieList[p1].nameMovie
        p0.count.text = movieList[p1].yearMovie.toString()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tvName)
        val count = itemView.findViewById<TextView>(R.id.tvCount)

    }

}