package com.tomtom.tom.domain.boundaries

import com.tomtom.tom.domain.model.Movie
import com.tomtom.tom.domain.model.MoviesResponse
import io.reactivex.Single

interface Interactor {

    interface Presentation {
        fun onMoviesPageDownloaded(response: MoviesResponse)
        fun onMoviesPageDownloadFailed(error:Throwable)
        fun onMoviesListSorted(list:List<Movie>)
    }

    interface Backend {
        fun downloadMovies(api_key:String, page:String = "1"):Single<MoviesResponse>
    }

}