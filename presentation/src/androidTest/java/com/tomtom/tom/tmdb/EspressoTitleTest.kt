package com.tomtom.tom.tmdb

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.TextView
import com.tomtom.tom.tmdb.ui.main.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class EspressoTitleTest {

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun listTitleTest() {
        Thread.sleep(1000)
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar))))
                .check(matches(withText(mActivityRule.activity.getString(R.string.list_screen_title))))
    }

    @Test
    fun detailsTitleTest() {
        Thread.sleep(1000)
        val showIndex = 3
        val showTitle = mActivityRule.activity.listFragment.adapter.movies[showIndex].title
        onView(ViewMatchers.withId(R.id.movies_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition<DetailsPagerAdapter.ViewHolder>(3, click()))

        Thread.sleep(1000)
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar))))
                .check(matches(withText(showTitle)))
    }
}