package com.tomtom.tom.tmdb.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.tomtom.tom.domain.model.Movie
import com.tomtom.tom.tmdb.R
import com.tomtom.tom.tmdb.application.MoviesListApplication
import com.tomtom.tom.tmdb.application.MoviesListApplication.Companion.baseUrl
import com.tomtom.tom.tmdb.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject


class MovieDetailFragment : BaseFragment(), MovieDetailsContract.View {

    @Inject
    lateinit var movieDetailsPresenter: MovieDetailsPresenter

    var presenter: MovieDetailsContract.Presenter

    init {
        MoviesListApplication.presenterComponent.inject(this)
        presenter = movieDetailsPresenter
        presenter.setFragment(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun onDataUpdate(movie: Movie) {
        detail_title.text = movie.title
        detail_overview.text = movie.overview
        Picasso.with(context)
                .load(baseUrl + movie.poster_path)
                .placeholder(R.drawable.ic_movie)
                .into(detail_image)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}
