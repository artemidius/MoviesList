package com.tomtom.tom.tmdb.dagger

import com.tomtom.tom.tmdb.ui.detail.MovieDetailsPresenter
import com.tomtom.tom.tmdb.ui.list.MoviesListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule(
        val moviesListPresenter: MoviesListPresenter,
        val moviesDetailsPresenter: MovieDetailsPresenter) {
    @Provides
    @Singleton
    fun provideMoviesListPresenter(): MoviesListPresenter = moviesListPresenter

    @Provides
    @Singleton
    fun provideMovieDetailsPresenter(): MovieDetailsPresenter = moviesDetailsPresenter
}