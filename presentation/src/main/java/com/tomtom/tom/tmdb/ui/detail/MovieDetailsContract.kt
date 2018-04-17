package com.tomtom.tom.tmdb.ui.detail

import com.tomtom.tom.domain.model.Movie
import com.tomtom.tom.tmdb.base.ActivityLifeCyclePresenter


interface MovieDetailsContract {

    interface View {
        fun onDataUpdate(movie:Movie)
    }

    interface Presenter : ActivityLifeCyclePresenter {
        fun onViewCreated()
        fun initializeDataset(movie: Movie)
        fun setFragment(fragment: MovieDetailFragment)
    }

}