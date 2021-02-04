package com.example.myacademy.view.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.example.myacademy.R
import com.example.myacademy.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{

    private val dummyCourse = DataDummy.generateDummyCourses()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun loadCourses() {
        onView(withId(R.id.rv_academy)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourse.size))
    }

    @Test
    fun loadDetailCourse() {
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.text_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.text_title)).check(ViewAssertions.matches(withText(dummyCourse[0].title)))
        onView(withId(R.id.text_date)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.text_date)).check(ViewAssertions.matches(ViewMatchers.withText("Deadline ${dummyCourse[0].deadline}")))
    }

    @Test
    fun loadModule() {
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.btn_start)).perform(click())
        onView(withId(R.id.rv_moduleList)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadDetailModule() {
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.btn_start)).perform(ViewActions.click())
        onView(withId(R.id.rv_moduleList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.web_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadBookmarks() {
        onView(ViewMatchers.withText("Bookmark")).perform(ViewActions.click())
        onView(withId(R.id.rv_bookmark)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_bookmark)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourse.size))
    }
}