package com.tomtom.tom.tmdb.ui.detail

import android.util.Log
import com.tomtom.tom.domain.model.Movie
import com.tomtom.tom.tmdb.base.BasePresenter


class MovieDetailsPresenter(private val detailFragment: DetailFragment) : BasePresenter(), MovieDetailsContract.Presenter {

    private val tag = this.javaClass.simpleName
    private val view: MovieDetailsContract.View? = detailFragment
    lateinit var currentMovie: Movie
    private var fragmentIsActive = false

    override fun initializeDataset(movie: Movie) {
        currentMovie = movie
    }

    private fun updateUI(movie: Movie) {
        if (fragmentIsActive) {
            detailFragment.activity.runOnUiThread {
                view?.onDataUpdate(movie)
            }
        }
    }

    override fun onViewCreated() {
        fragmentIsActive = true
        updateUI(currentMovie)
    }

    override fun onStop()         { fragmentIsActive = false                             }
    override fun onCreate()       {  Log.d(tag, "Fragment triggered onResume()")    }
    override fun onResume()       {  Log.d(tag, "Fragment triggered onResume()")    }
    override fun onPause()        {  Log.d(tag, "Fragment triggered onPause()")     }
    override fun onDestroy()      {  Log.d(tag, "Fragment triggered onDestroy()")   }

}
