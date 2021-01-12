package com.monschein.movie.presentation.bookmark

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
import com.monschein.movie.presentation.search.SearchAdapter

class BookmarkFragment : Fragment(), SearchAdapter.OnSearchItemClickListener {

    companion object {
        fun newInstance() = BookmarkFragment()
    }

    private lateinit var ProgressBar: ProgressBar
    private lateinit var RecyclerView: RecyclerView

    private lateinit var adapter: SearchAdapter

    private val viewModel: BookmarkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ProgressBar = view.findViewById(R.id.fragment_movie_search_ProgressBar)
        RecyclerView = view.findViewById(R.id.fragment_movie_search_RecyclerView)

        adapter = SearchAdapter(requireContext(), this)
        RecyclerView.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner, ::updateState)

        viewModel.getBookmark()
    }

    private fun updateState(state: BookmarkState) {
        when (state) {
            is BookmarkState.ErrorState -> {
                ProgressBar.isVisible = false
                adapter.setData(null)

                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG)
            }
            is BookmarkState.LoadingState -> {
                ProgressBar.isVisible = true
                adapter.setData(null)
            }
            is BookmarkState.SuccessState -> {
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