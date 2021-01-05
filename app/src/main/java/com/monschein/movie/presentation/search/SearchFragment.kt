package com.monschein.movie.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.monschein.movie.R
import com.monschein.movie.presentation.MainActivity


class SearchFragment : Fragment(), SearchAdapter.OnSearchItemClickListener {

    private lateinit var Button: Button
    private lateinit var EditText: EditText
    private lateinit var ProgressBar: ProgressBar
    private lateinit var RecyclerView: RecyclerView

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Button = view.findViewById(R.id.fragment_movie_search_Button)
        EditText = view.findViewById(R.id.fragment_movie_search_EditText)
        ProgressBar = view.findViewById(R.id.fragment_movie_search_ProgressBar)
        RecyclerView = view.findViewById(R.id.fragment_movie_search_RecyclerView)

        adapter = SearchAdapter(requireContext(), this)
        RecyclerView.adapter = adapter

        Button.setOnClickListener {
            viewModel.searchMovie(EditText.text.toString())
        }

        viewModel.state.observe(viewLifecycleOwner, ::updateState)
    }

    private fun updateState(state: SearchState) {
        when (state) {
            is SearchState.ErrorState -> {
                ProgressBar.isVisible = false
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG)
                adapter.setData(null)
            }
            is SearchState.LoadingState -> {
                ProgressBar.isVisible = true
                adapter.setData(null)

            }
            is SearchState.SuccessState -> {
                ProgressBar.isVisible = false
                adapter.setData(state.movies)

            }
        }
    }

    override fun onSearchItemClick(id: String) {
        val activity: MainActivity? = activity as? MainActivity

        activity?.displayMovieDetail(id)
    }
}