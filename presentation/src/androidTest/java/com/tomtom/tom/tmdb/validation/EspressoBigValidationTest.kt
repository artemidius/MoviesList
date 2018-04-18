package com.tomtom.tom.tmdb.validation


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.tomtom.tom.tmdb.R
import com.tomtom.tom.tmdb.adapters.MoviesListAdapter
import com.tomtom.tom.tmdb.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class EspressoBigValidationTest {

    private val numberOfSwipes = 10
    private val numberOfTaps = 10
    private val delay:Long = 1000

    @Rule @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    private fun getRandomItemOfTheList(listSize:Int):Int {
        return when {
            listSize > 0 -> Random().nextInt(listSize - 1)
            else -> 0
        }
    }

    @Test
    fun bigFunctionalityTest() {


        Thread.sleep(5000)

        //some swipes on movies list forward
        for (x in 0 .. numberOfSwipes) {
            onView(withId(R.id.list_container)).perform(swipeUp())
            Thread.sleep(delay)
        }

        //some taps on sort button
        for (x in 0 .. numberOfTaps) {
            onView(withId(R.id.sort_button)).perform(click())
            Thread.sleep(delay)
        }

        //some swipes on movies list back
        for (x in 0 .. numberOfSwipes) {
            onView(withId(R.id.movies_recycler)).perform(swipeDown())
            Thread.sleep(delay)
        }

        //tap on movie
        onView(ViewMatchers.withId(R.id.movies_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition<MoviesListAdapter.ViewHolder>(getRandomItemOfTheList(mActivityRule.activity.listFragment.adapter.movies.size), ViewActions.click()))


        Thread.sleep(1000)
        onView(isRoot())
                .perform(ViewActions.pressBack())

        //check back press behaviour
        onView(withId(R.id.movies_recycler)).check(matches(isDisplayed()))

       //check that activity is alive
        Thread.sleep(1000)

    }
}