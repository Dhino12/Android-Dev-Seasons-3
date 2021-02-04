package com.example.moviecatalog

import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalog.utils.DataDummy
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import java.io.File


class MainActivityTest{

    private val movieDummy = DataDummy.generateDummyMovie()
    private val tvDummy = DataDummy.generateDummyTV()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)
    val intentTestRule = IntentsTestRule(MainActivity::class.java)

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
        onView(withId(R.id.tv_yearsRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_yearsRelease)).check(matches(withText(movieDummy[0].yearPublish)))
        onView(withId(R.id.tvTitleFilms)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitleFilms)).check(matches(withText(movieDummy[0].titleMovie)))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(withText(movieDummy[0].duration)))
        onView(withId(R.id.tv_director)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_director)).check(matches(withText(movieDummy[0].director)))
        onView(withId(R.id.tv_screenPlay)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_screenPlay)).check(matches(withText(movieDummy[0].screenPlay)))
        onView(withId(R.id.tv_ReleaseDateDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ReleaseDateDetail)).check(matches(withText(movieDummy[0].dateRelease)))
        onView(withId(R.id.scrollViewDetail)).perform(swipeUp())
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(withText("Rilis")))
        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).check(matches(withText(movieDummy[0].description)))
    }

    @Test
    fun loadDetailTV(){
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.rvFilms)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_yearsRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_yearsRelease)).check(matches(withText(tvDummy[0].yearPublish)))
        onView(withId(R.id.tvTitleFilms)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitleFilms)).check(matches(withText(tvDummy[0].titleTv)))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(withText(tvDummy[0].duration)))
        onView(withId(R.id.tv_director)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_director)).check(matches(withText(tvDummy[0].writer)))
        onView(withId(R.id.tv_screenPlay)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_screenPlay)).check(matches(withText(tvDummy[0].kreator)))
        onView(withId(R.id.tv_ReleaseDateDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ReleaseDateDetail)).check(matches(withText(tvDummy[0].broadcast_date)))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
        onView(withId(R.id.scrollViewDetail)).perform(swipeUp())
        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).check(matches(withText(tvDummy[0].description)))
    }
}