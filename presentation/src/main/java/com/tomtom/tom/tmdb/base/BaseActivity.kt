package com.tomtom.tom.tmdb.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tomtom.tom.domain.model.Movie
import com.tomtom.tom.tmdb.ui.list.MoviesListContract

open class BaseActivity : AppCompatActivity(), Dispatcher {

    override fun onConnectionFailed(presenter: MoviesListContract.Presenter) { }

    override fun showLoadigProgress(visible: Boolean) { }

    override fun navigateTo(fragment: String, movie: Movie?) { }

    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) }
}