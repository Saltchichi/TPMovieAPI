package com.monschein.movie.presentation.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.internal.`$Gson$Preconditions`
import com.monschein.movie.R
import com.monschein.movie.domain.model.MovieShort
import com.squareup.picasso.Picasso

class SearchAdapter(context: Context, val listener: OnSearchItemClickListener) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    interface OnSearchItemClickListener {
        fun onSearchItemClick(id: String)
    }

    private val movies: ArrayList<MovieShort> = ArrayList()

    private val inflater = LayoutInflater.from(context)

    override fun getItemCount() = movies.size

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

        notifyDataSetChanged()

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.title)
        private val poster: ImageView = view.findViewById(R.id.poster)

        init {
            view.setOnClickListener {
                listener.onSearchItemClick(movies[adapterPosition].id)
            }
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