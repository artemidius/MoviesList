package com.tomtom.tom.tmdb.ui.list

import com.tomtom.tom.domain.model.Movie
import com.tomtom.tom.tmdb.base.ActivityLifeCyclePresenter


interface MoviesListContract {
    interface View {
        fun onDataUpdate(movies:List<Movie>)
    }

    interface Presenter : ActivityLifeCyclePresenter {
        fun onViewCreated()
        fun onItemClick(movie: Movie?)
        fun downloadNextPage()
    }

}