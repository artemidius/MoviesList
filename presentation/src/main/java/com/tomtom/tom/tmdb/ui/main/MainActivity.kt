package com.tomtom.tom.tmdb.ui.main

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import com.tomtom.tom.domain.model.Movie
import com.tomtom.tom.tmdb.R
import com.tomtom.tom.tmdb.base.ActivityLifeCyclePresenter
import com.tomtom.tom.tmdb.base.BaseActivity
import com.tomtom.tom.tmdb.base.BaseFragment
import com.tomtom.tom.tmdb.base.Dispatcher
import com.tomtom.tom.tmdb.base.Dispatcher.Companion.DETAILS_FRAGMENT
import com.tomtom.tom.tmdb.base.Dispatcher.Companion.LIST_FRAGMENT
import com.tomtom.tom.tmdb.ui.detail.DetailFragment
import com.tomtom.tom.tmdb.ui.detail.MovieDetailsContract
import com.tomtom.tom.tmdb.ui.list.MoviesListContract
import com.tomtom.tom.tmdb.ui.list.MoviesListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.*

class MainActivity : BaseActivity(), Dispatcher {

    private lateinit var bottomSheetBehavior:BottomSheetBehavior<View>

    private val dispatcher: Dispatcher = this
    val listFragment = MoviesListFragment()
    val detailFragment = DetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        showLoadigProgress(false)

        listFragment.dispatcher = dispatcher
        detailFragment.dispatcher = dispatcher

        navigateTo(LIST_FRAGMENT)
    }

    override fun navigateTo(fragment: String, movie: Movie?) {
        super.navigateTo(fragment, movie)
        when (fragment) {
            LIST_FRAGMENT -> addFragment(listFragment, false)
            DETAILS_FRAGMENT -> {
                if(movie != null) {
                    addFragment(detailFragment)
                    detailFragment.presenter.initializeDataset(movie)
                }
            }
            else -> Log.d(this.javaClass.simpleName, "Unknown fragment to navigate")
        }
    }

    private fun addFragment(fragment: BaseFragment, addToBack:Boolean = true) {
        if(addToBack) supportFragmentManager.beginTransaction().replace(R.id.fragments_container, fragment).addToBackStack(null).commit()
        else supportFragmentManager.beginTransaction().replace(R.id.fragments_container, fragment).commit()
    }

    override fun showLoadigProgress(visible: Boolean) {
        runOnUiThread {
            bottomSheetBehavior.state = if (visible) BottomSheetBehavior.STATE_EXPANDED else BottomSheetBehavior.STATE_HIDDEN
        }
    }

    override fun onConnectionFailed(presenter:MoviesListContract.Presenter) {
        Snackbar.make(main_host, getString(R.string.connection_failed), Snackbar.LENGTH_INDEFINITE).setAction(getString(R.string.retry)) {
                    presenter.downloadNextPage()
                }.show()
    }

    override fun onBackPressed() {
        val count = fragmentManager.backStackEntryCount
        if (count == 0) super.onBackPressed()
        else fragmentManager.popBackStack()
    }
}