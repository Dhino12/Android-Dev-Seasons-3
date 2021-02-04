package com.example.myidlingresource

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingRegistry.getInstance
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    @get:Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    // Tambahan IdlingResource =============
    /*
    * @Before digunakan untuk menyiapkan apa saja yang perlu disiapkan sebelum pengujian.
    * Sedangkan @After digunakan untuk memberi aksi setelah pengujian selesai.
    * Kode di atas melakukan register dan unregister untuk Idling Resource.
    * */
    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }
    // ===========================

    @Test
    fun checkText(){
        onView(withId(R.id.textView)).check(matches(withText(mActivityRule.activity.getString(R.string.prepare))))
        onView(withText(mActivityRule.activity.getString(R.string.start))).perform(click())
        onView(withId(R.id.textView)).check(matches(withText(mActivityRule.activity.getString(R.string.delay1))))
    }

    private fun delay(){
        try {
            Thread.sleep(2000)
        }catch (e:InterruptedException){
            e.printStackTrace()
        }
    }
}