package com.gorillamo.instant.relationship

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gorillamo.app.relationship.InjectedBaseActivityTest
import com.gorillamo.app.relationship.R

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule


/**
 * Test that the Relationship list is displaying correctly
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RelationshipListActivityTest {



    @get:Rule
    val mActivityRule = InjectedBaseActivityTest(
        RelationshipListActivity::class.java,
        initialTouchMode = true,
        launchActivity = false
    )


    /**
     * Displays a Search Bar
     */
    @Test
    fun completelyDisplaysRecyclerView(){


        mActivityRule.launchActivity(null)
        onView(withId(R.id.relationship_list)).check(matches(isDisplayed()))
    }

    @Test
    fun showsNameAndLastSeenOfAnItem(){

        mActivityRule.launchActivity(null)
        onView(withText("1")).check(matches(isDisplayed()))
        onView(withText("Item 1")).check(matches(isDisplayed()))}

}
