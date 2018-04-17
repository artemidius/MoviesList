package com.tomtom.tom.tmdb.ui.list

import android.app.ListFragment
import android.util.Log
import com.tomtom.tom.data.backend.BackendRepository
import com.tomtom.tom.domain.boundaries.DownloadMoviesUseCase
import com.tomtom.tom.domain.boundaries.Interactor
import com.tomtom.tom.domain.boundaries.SortMoviesByDateDescUseCase
import com.tomtom.tom.domain.model.Movie
import com.tomtom.tom.domain.model.MoviesResponse
import com.tomtom.tom.domain.usecases.DownloadMoviesUseCaseImpl
import com.tomtom.tom.domain.usecases.SortMoviesByDateDescUseCaseImpl
import com.tomtom.tom.tmdb.R
import com.tomtom.tom.tmdb.application.MoviesListApplication.Companion.apiKey
import com.tomtom.tom.tmdb.base.BasePresenter
import com.tomtom.tom.tmdb.base.Dispatcher


class MoviesListPresenter : BasePresenter(), MoviesListContract.Presenter, Interactor.Presentation {


    private val tag = this.javaClass.simpleName
    lateinit var view: MoviesListContract.View
    lateinit var listFragment: MoviesListFragment
    private val downloadMoviesUseCase: DownloadMoviesUseCase = DownloadMoviesUseCaseImpl()
    private val sortMoviesByDateDescUseCase: SortMoviesByDateDescUseCase = SortMoviesByDateDescUseCaseImpl()
    private val backendInteractor: Interactor.Backend = BackendRepository()
    private val presenter = this
    private var fragmentIsActive = false

    companion object {
        var currentPage: Int = 0
        var moviesList = mutableListOf<Movie>()
        var sortedList = listOf<Movie>()
        var sortedByDate = false
    }

    override fun onMoviesPageDownloaded(response: MoviesResponse) {
        currentPage = response.page
        moviesList.addAll(response.results)
        sortMoviesByDateDescUseCase.run(moviesList, this)
    }

    override fun setFragment(fragment:MoviesListFragment) {
        listFragment = fragment
        view = listFragment
    }

    override fun onMoviesListSorted(list: List<Movie>) {
        sortedList = list
        updateUI()
    }

    private fun updateUI() {
        if (fragmentIsActive) {
            listFragment.activity.runOnUiThread {
                listFragment.dispatcher.showLoadigProgress(false)
                if(sortedByDate) view?.onDataUpdate(sortedList)
                else view?.onDataUpdate(moviesList)
            }
        }
    }

    override fun onMoviesPageDownloadFailed(error: Throwable) {
        listFragment.dispatcher.onConnectionFailed(this)
        listFragment.dispatcher.showLoadigProgress(false)
        updateUI()
    }

    override fun onItemClick(movie: Movie?) {
        listFragment.dispatcher.showLoadigProgress(false)
        listFragment.dispatcher.navigateTo(Dispatcher.DETAILS_FRAGMENT, movie)
    }

    override fun downloadNextPage() {
        listFragment.dispatcher.showLoadigProgress(true)
        downloadMoviesUseCase.run(apiKey, currentPage, backendInteractor, presenter)
    }

    override fun onViewCreated() {
        fragmentIsActive = true
        updateScreenTitle()
        updateButtonText()
        if (moviesList.size < 20) downloadNextPage()
        else view?.onDataUpdate(moviesList)
    }

    fun updateScreenTitle() {
        listFragment.activity.title = if(sortedByDate) context.getString(R.string.list_screen_title_sorted) else context.getString(R.string.list_screen_title_unsorted)
    }

    fun updateButtonText() {
        view?.setSortingButtonState(sortedByDate)
    }

    override fun onSortButtonClick() {
        sortedByDate = !sortedByDate
        updateUI()
        view?.setSortingButtonState(sortedByDate)
        updateScreenTitle()
    }

    override fun onCreate()       {  Log.d(tag, "Fragment triggered onCreate()")    }
    override fun onResume()       {
        Log.d(tag, "Fragment triggered onResume()")

    }
    override fun onPause()        {  Log.d(tag, "Fragment triggered onPause()")     }
    override fun onDestroy()      {  Log.d(tag, "Fragment triggered onDestroy()")   }
    override fun onStop()         {  fragmentIsActive = false                            }
}

