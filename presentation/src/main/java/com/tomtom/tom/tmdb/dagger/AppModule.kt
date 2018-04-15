package com.tomtom.tom.tmdb.dagger

import android.content.Context
import com.tomtom.tom.tmdb.application.MoviesListApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: MoviesListApplication) {
    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication(): MoviesListApplication = app
}