package com.monschein.movie.presentation.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monschein.movie.R
import com.monschein.movie.domain.model.MovieShort
import com.squareup.picasso.Picasso

class SearchAdapter(context: Context) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private val movies: ArrayList<MovieShort> = ArrayList()

    override fun getItemCount() = movies.size

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun setData(movies: List<MovieShort>?) {
        this.movies.clear()

        movies?.let {
            this.movies.addAll(movies)
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.title)
        private val poster: ImageView = view.findViewById(R.id.poster)

        init {
            view.setOnClickListener {}
        }

        fun bind(movieShort: MovieShort) {
            title.text = movieShort.title

            if (movieShort.poster.isNotEmpty() && movieShort.poster.isNotBlank()) {
                Picasso.get().load(movieShort.poster).into(poster)
            } else {
                poster.setImageDrawable(null)
            }
        }

    }
}