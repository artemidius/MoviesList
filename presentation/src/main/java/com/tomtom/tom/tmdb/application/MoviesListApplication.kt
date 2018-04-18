package com.tomtom.tom.tmdb.application

import android.app.Application
import com.tomtom.tom.tmdb.R
import com.tomtom.tom.tmdb.dagger.*
import com.tomtom.tom.tmdb.ui.detail.MovieDetailsPresenter
import com.tomtom.tom.tmdb.ui.list.MoviesListPresenter
import com.tomtom.tom.tmdb.dagger.PresenterModule
import io.sentry.Sentry
import io.sentry.android.AndroidSentryClientFactory


class MoviesListApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var presenterComponent: PresenterComponent
        lateinit var apiKey: String
        lateinit var baseUrl: String
    }

    override fun onCreate() {
        super.onCreate()

        /*
        OKAY,
        I know that hardcoding a secret strings is totally illegal.
        I do it as an exception for the sake of a test work
        */
        apiKey = resources.getString(R.string.api_key)

        val sentryDsn = getString(R.string.sentry_key)
        Sentry.init(sentryDsn, AndroidSentryClientFactory(this))
        Sentry.capture("Application starts")

        baseUrl = resources.getString(R.string.base_url)

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        presenterComponent = DaggerPresenterComponent.builder()
                .presenterModule(PresenterModule(MoviesListPresenter(), MovieDetailsPresenter()))
                .build()
    }
}