package com.tomtom.tom.tmdb.ui.detail

import android.util.Log
import com.tomtom.tom.domain.model.Movie
import com.tomtom.tom.tmdb.base.BasePresenter


class MovieDetailsPresenter() : BasePresenter(), MovieDetailsContract.Presenter {

    private val tag = this.javaClass.simpleName
    lateinit var view: MovieDetailsContract.View
    lateinit var currentMovie: Movie
    private var fragmentIsActive = false
    lateinit var movieDetailFragment:MovieDetailFragment

    override fun initializeDataset(movie: Movie) {
        currentMovie = movie
    }

    override fun setFragment(fragment: MovieDetailFragment) {
        movieDetailFragment = fragment
        view = movieDetailFragment
    }

    private fun updateUI(movie: Movie) {
        if (fragmentIsActive) {
            movieDetailFragment.activity.runOnUiThread {
                view.onDataUpdate(movie)
            }
        }
    }

    fun updateScreenTitle() {
        movieDetailFragment.activity.title = currentMovie.title
    }

    override fun onViewCreated() {
        fragmentIsActive = true
        updateScreenTitle()
        updateUI(currentMovie)
    }

    override fun onStop()         { fragmentIsActive = false                             }
    override fun onCreate()       {  Log.d(tag, "Fragment triggered onResume()")    }
    override fun onResume()       {  Log.d(tag, "Fragment triggered onResume()")    }
    override fun onPause()        {  Log.d(tag, "Fragment triggered onPause()")     }
    override fun onDestroy()      {  Log.d(tag, "Fragment triggered onDestroy()")   }

}
