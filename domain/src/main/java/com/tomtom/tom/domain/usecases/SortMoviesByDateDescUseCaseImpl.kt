package com.tomtom.tom.domain.usecases

import com.tomtom.tom.domain.boundaries.Interactor
import com.tomtom.tom.domain.boundaries.SortMoviesByDateDescUseCase
import com.tomtom.tom.domain.model.Movie

class SortMoviesByDateDescUseCaseImpl : SortMoviesByDateDescUseCase {

    override fun run(list:List<Movie>, presentationInteractor: Interactor.Presentation) {
        presentationInteractor.onMoviesListSorted(sortMoviesByDateDesc(list))
    }

    fun sortMoviesByDateDesc(list: List<Movie>): List<Movie> = list.sortedWith(compareBy({ it.release_date })).reversed()

}