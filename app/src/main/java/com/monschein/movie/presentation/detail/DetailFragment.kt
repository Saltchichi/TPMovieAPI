package com.monschein.movie.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.monschein.movie.R
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private lateinit var titreDetail: TextView
    private lateinit var descriptionDetail: TextView
    private lateinit var directorDetail: TextView
    private lateinit var actorsDetail: TextView
    private lateinit var metascoreDetail: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var image: ImageView
    private lateinit var bookmark: Button

    private val viewModel: DetailViewModel by viewModels()

    companion object {
        private const val KEY_ID = "key_id"

        fun newInstance(id: String): DetailFragment {
            val bundle = Bundle()
            bundle.putString(KEY_ID, id)

            val fragment = DetailFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titreDetail = view.findViewById(R.id.titre_detail)
        descriptionDetail = view.findViewById(R.id.description_detail)
        directorDetail = view.findViewById(R.id.director_detail)
        actorsDetail = view.findViewById(R.id.actors_detail)
        metascoreDetail = view.findViewById(R.id.metascore_detail)
        progressBar = view.findViewById(R.id.progress_bar)
        image = view.findViewById(R.id.poster_detail)
        bookmark = view.findViewById(R.id.fragment_movie_detail_bookmark_Button)

        viewModel.state.observe(viewLifecycleOwner, ::updateState)

        arguments?.getString(KEY_ID)?.let {
            viewModel.getMovieDetail(it)
        }
    }

    private fun updateState(state: DetailState) {
        when (state) {
            is DetailState.ErrorState -> {
                progressBar.isVisible = false
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()

                bookmark.setOnClickListener(null)
            }
            is DetailState.LoadingState -> {
                progressBar.isVisible = true

                bookmark.setOnClickListener(null)
            }
            is DetailState.SuccessState -> {
                progressBar.isVisible = false
                titreDetail.text = state.movie.title
                descriptionDetail.text = state.movie.description
                directorDetail.text = state.movie.director
                actorsDetail.text = state.movie.actors
                if (state.movie.poster.isNotEmpty() && state.movie.poster.isNotBlank()) {
                    Picasso.get().load(state.movie.poster).into(image)

                    bookmark.setOnClickListener {
                        arguments?.getString(KEY_ID)?.let {
                            viewModel.addBookmark(state.movie)
                        }
                    }
                }
            }
        }
    }
}