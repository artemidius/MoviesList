package com.tomtom.tom.tmdb.dagger

import com.tomtom.tom.tmdb.ui.detail.MovieDetailFragment
import com.tomtom.tom.tmdb.ui.list.MoviesListFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        PresenterModule::class
        )
)

interface PresenterComponent {
    fun inject(movieDetailFragment: MovieDetailFragment)
    fun inject(listFragment: MoviesListFragment)
}