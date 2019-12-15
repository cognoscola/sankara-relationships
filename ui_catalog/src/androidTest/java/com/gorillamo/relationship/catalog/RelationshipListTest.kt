package com.gorillamo.relationship.catalog

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test that the Relationship list is displaying correctly
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RelationshipListActivityTest {

    @get:Rule
    val mActivityRule =
        com.gorillamo.relationship.catalog.InjectedBaseActivityTest(
            ItemListActivity::class.java,
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
