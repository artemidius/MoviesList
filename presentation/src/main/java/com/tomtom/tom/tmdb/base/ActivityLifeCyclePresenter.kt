package com.tomtom.tom.tmdb.base

interface ActivityLifeCyclePresenter {
    fun onCreate()
    fun onResume()
    fun onPause()
    fun onStop()
    fun onDestroy()

}