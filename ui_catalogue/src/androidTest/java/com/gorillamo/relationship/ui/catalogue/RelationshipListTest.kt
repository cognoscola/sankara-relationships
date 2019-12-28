package com.gorillamo.relationship.ui.catalogue

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.startsWith
//import androidx.test.rule.ActivityTestRule
//import com.gorillamo.relationship.abstraction.dto.Relationship
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.core.context.loadKoinModules
//import org.koin.core.context.stopKoin
//import org.koin.dsl.module
//import org.koin.test.KoinTest
//import org.mockito.Mockito
//import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class RelationshipListTest {
//class RelationshipTestWithMockViewModel: KoinTest {
    @Suppress("unused")
    private val tag:String = RelationshipListTest::class.java.name

    @Rule
    @JvmField
    val rule = ActivityTestRule(CatalogueTestActivity::class.java, true, false)

//    lateinit var mockVm: RelationshipViewModel

    @Before
    fun setup() {
/*
        mockVm = mock(RelationshipViewModel::class.java)

        */
/**
         * As Activity only requires viewModel.
         * also note, we override Application class as TestApp to avoid modules from main app.
         *//*

        loadKoinModules(module {
            viewModel {
                mockVm
            }
        })
*/
    }

    @After
    fun cleanUp() {
//        stopKoin()
    }

    @Test
    fun should_display_relationships_when_activity_loads() {
        val activity = rule.launchActivity(null)

        activity.replaceItems(generateRelationshipList())

        onView(withId(R.id.relationship_list)).check(matches(isCompletelyDisplayed()))
        onView(withText("name 0")).check(matches(isDisplayed()))
    }

    @Test
    fun should_display_last_contacted_in_terms_of_days(){

        val activity = rule.launchActivity(null)

        activity.replaceItems(generateRelationshipList())
        Thread.sleep(500)

        onView(withText("Yesterday")).check(matches(isDisplayed()))
        onView(withText("2 days ago")).check(matches(isDisplayed()))
        onView(withText("3 days ago")).check(matches(isDisplayed()))
        onView(withText("4 days ago")).check(matches(isDisplayed()))
    }

    @Test
    fun should_show_bottom_navigation_with_two_tabs(){
        val activity = rule.launchActivity(null)

        activity.replaceItems(generateRelationshipList())

        Thread.sleep(500)

        onView(withText("Today")).check(matches(isCompletelyDisplayed()))
        onView(withText("All Relationships")).check(matches(isCompletelyDisplayed()))


    }

    @Test
    fun shows_dialog_fragment_when_new_item_clicked(){
        rule.launchActivity(null)
        onView(withId(R.id.addFab)).perform(click())
        Thread.sleep(500)
        onView(withText(startsWith("How often"))).check(matches(isDisplayed()))

    }

    @Test
    fun adds_item_to_list_view_when_item_saved(){
        val name = "Dr Amen"
        rule.launchActivity(null)
        onView(withId(R.id.addFab)).perform(click())
        Thread.sleep(500)

        onView(withId(R.id.nameEditText)).perform(ViewActions.typeText(name))
        onView(withId(R.id.saveButton)).perform(click())

        onView(withText(name)).check(matches(isDisplayed()))
    }

    @Test
    fun item_is_removed_from_list_when_delete_in_dialog_is_pressed(){

        val activity = rule.launchActivity(null)

        activity.replaceItems(generateRelationshipList())

        Thread.sleep(500)

        onView(withText("name 0")).perform(click())

        Thread.sleep(200)

        onView(withText("Delete")).perform(click())

        onView(withText("name 0")).check(matches(not(isDisplayed())))

    }

    private fun generateRelationshipList():List<RelationshipItemAdapter.RelationshipItem>{
        return List(5) { i ->

            val time = today() - days(i)
            val item = RelationshipItemAdapter.RelationshipItem(
                id = i,
                name = "name $i",
                timeLastContacted = time,
                ready = true,
                count = 1,
                range = 1


            )
            System.out.println("Time is $time");
            item
        }
    }

    private fun today() = System.currentTimeMillis()

    private fun days(day:Int) = oneDayInMillis() * day

    private fun oneDayInMillis():Long{
        return 1000 * 60 * 60 * 24
    }

}