package com.tomtom.tom.tmdb

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tomtom.tom.tmdb.ui.list.MoviesListFragment
import com.tomtom.tom.tmdb.ui.list.MoviesListPresenter
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ContextInPresenterTest {
    @Test
    @Throws(Exception::class)
    fun testContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val presenterContext = MoviesListPresenter(MoviesListFragment()).context
        assertEquals(presenterContext.packageName, appContext.packageName)
    }
}