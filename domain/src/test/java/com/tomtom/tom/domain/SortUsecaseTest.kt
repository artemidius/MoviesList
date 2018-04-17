package com.tomtom.tom.domain

import com.tomtom.tom.domain.model.Movie
import com.tomtom.tom.domain.usecases.SortMoviesByDateDescUseCaseImpl
import org.junit.Assert.assertEquals
import org.junit.Test

class SortUsecaseTest {

    @Test
    fun sortWorksFine() {

        val movie1 = Movie("title", "path", "overview", 0, 0.0, "2015-12-31")
        val movie2 = Movie("title", "path", "overview", 0, 0.0, "2016-11-16")
        val movie3 = Movie("title", "path", "overview", 0, 0.0, "2017-04-17")
        val movie4 = Movie("title", "path", "overview", 0, 0.0, "2018-04-18")


        val list = mutableListOf<Movie>()

        list.add(movie1)
        list.add(movie3)
        list.add(movie2)
        list.add(movie4)

        val sortedList = SortMoviesByDateDescUseCaseImpl().sortMoviesByDateDesc(list)

        assertEquals(movie4, sortedList[0])
        assertEquals(movie3, sortedList[1])
        assertEquals(movie2, sortedList[2])
        assertEquals(movie1, sortedList[3])

    }
}