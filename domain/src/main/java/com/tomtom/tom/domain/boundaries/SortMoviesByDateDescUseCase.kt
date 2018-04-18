package com.tomtom.tom.domain.boundaries

import com.tomtom.tom.domain.model.Movie

interface SortMoviesByDateDescUseCase {
    fun run(
            list:List<Movie>,
            presentationInteractor: Interactor.Presentation
    )
}