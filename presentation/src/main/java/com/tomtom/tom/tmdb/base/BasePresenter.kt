package com.tomtom.tom.tmdb.base

import android.content.Context
import com.tomtom.tom.tmdb.application.MoviesListApplication
import javax.inject.Inject

open class BasePresenter {
    @Inject
    lateinit var context: Context

    @Inject
    lateinit var application: MoviesListApplication

    init {
        MoviesListApplication.appComponent.inject(this)
    }
}