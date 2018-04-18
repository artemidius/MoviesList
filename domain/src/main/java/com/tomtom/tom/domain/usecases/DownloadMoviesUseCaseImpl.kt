package com.tomtom.tom.domain.usecases

import com.tomtom.tom.domain.boundaries.DownloadMoviesUseCase
import com.tomtom.tom.domain.boundaries.Interactor
import com.tomtom.tom.domain.model.MoviesResponse
import java.util.*
import java.util.concurrent.TimeUnit

class DownloadMoviesUseCaseImpl : DownloadMoviesUseCase {

    override fun run(api_key: String, page: Int, backendInteractor: Interactor.Backend, presentationInteractor: Interactor.Presentation) {
        val nextPage: String = (page + 1).toString()
        val randomDelay = Random().nextInt(1000).toLong() + 500
        backendInteractor.downloadMovies(api_key, nextPage)
                .timeout(7, TimeUnit.SECONDS)
                .retry (2)
                .subscribe { response: MoviesResponse?, error: Throwable? ->
                    when {
                        error != null -> presentationInteractor.onMoviesPageDownloadFailed(error)
                        response == null -> presentationInteractor.onMoviesPageDownloadFailed(Throwable("Response was null"))
                        else -> presentationInteractor.onMoviesPageDownloaded(response)
                    }
                }
    }
}