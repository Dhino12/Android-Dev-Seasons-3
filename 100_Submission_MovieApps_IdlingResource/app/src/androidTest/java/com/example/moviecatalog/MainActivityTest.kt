package com.example.moviecatalog

import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*

import androidx.test.rule.ActivityTestRule

import com.example.moviecatalog.utils.DataDummy
import com.example.moviecatalog.utils.EspressoIdlingResource

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    private val movieDummy = DataDummy.generateDummyMovie()
    private val tvDummy = DataDummy.generateDummyTV()
    private val movieUpcoming = DataDummy.generateUpcominglDummyMovie()
    private val tvUpcoming = DataDummy.generateUpcominglDummyTv()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)

    }

    @Test
    fun loadMovie(){

        onView(withId(R.id.navigation_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_movie)).perform(click())

        onView(withId(R.id.rvFilms)).check(matches(isDisplayed()))
        onView(withId(R.id.rvFilms)).perform(scrollToPosition<RecyclerView.ViewHolder>(movieDummy.size))

        onView(withId(R.id.navigation_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv)).perform(click())

        onView(withId(R.id.rvFilms)).check(matches(isDisplayed()))
        onView(withId(R.id.rvFilms)).perform(scrollToPosition<RecyclerView.ViewHolder>(tvDummy.size))
    }

    @Test
    fun loadDetailMovie(){

        onView(withId(R.id.rvFilms)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tvTitleFilms)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))

        onView(withId(R.id.scrollViewDetail)).perform(swipeUp())

        onView(withId(R.id.tv_ReleaseDateDetail)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(withText("Rilis")))

        onView(withId(R.id.rvGenre)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailMovieTrailer(){
        onView(withId(R.id.rvFilms)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withText("Trailer")).perform(click())

        onView(withId(R.id.rvTrailer)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTvTrailer(){
        onView(withId(R.id.navigation_tv)).perform(click())

        onView(withId(R.id.rvFilms)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withText("Trailer")).perform(click())

        onView(withId(R.id.rvTrailer)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTV(){
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.rvFilms)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tvTitleFilms)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))

        onView(withId(R.id.scrollViewDetail)).perform(swipeUp())

        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))

        onView(withId(R.id.rvGenre)).check(matches(isDisplayed()))
    }

    @Test
    fun loadUpcomingFilm(){

        onView(withId(R.id.rvHorFilms)).check(matches(isDisplayed()))
        onView(withId(R.id.rvHorFilms)).perform(scrollToPosition<RecyclerView.ViewHolder>(movieUpcoming.size))

        onView(withId(R.id.navigation_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv)).perform(click())

        onView(withId(R.id.rvHorFilms)).check(matches(isDisplayed()))
        onView(withId(R.id.rvHorFilms)).perform(scrollToPosition<RecyclerView.ViewHolder>(tvUpcoming.size))
    }
}