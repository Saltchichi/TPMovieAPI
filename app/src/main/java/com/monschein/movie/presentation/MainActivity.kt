package com.monschein.movie.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.monschein.movie.R
import com.monschein.movie.presentation.detail.DetailFragment
import com.monschein.movie.presentation.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private var container2: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        container2 = findViewById(R.id.fragment_container2)

        supportFragmentManager.commit {
            add(R.id.fragment_container, SearchFragment())
        }

    }

    fun displayMovieDetail(id: String) {

        if (container2 != null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container2, DetailFragment.newInstance(id))
            }
        } else {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, DetailFragment.newInstance(id))
                addToBackStack(null)
            }
        }
    }
}