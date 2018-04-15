package com.tomtom.tom.tmdb.dagger

import com.tomtom.tom.data.backend.BackendRepository
import com.tomtom.tom.tmdb.base.BasePresenter
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        AppModule::class
        )
)

interface AppComponent {
    fun inject(basePresenter: BasePresenter)
    fun inject(repository: BackendRepository)
}