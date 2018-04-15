package com.tomtom.tom.tmdb.base

import com.tomtom.tom.domain.model.Movie
import com.tomtom.tom.tmdb.ui.list.MoviesListContract


interface Dispatcher {
    companion object {
        const val LIST_FRAGMENT = "list"
        const val DETAILS_FRAGMENT = "details"
    }

    fun navigateTo(fragment:String, movie: Movie? = null)
    fun showLoadigProgress(visible:Boolean)
    fun onConnectionFailed(presenter: MoviesListContract.Presenter)

}