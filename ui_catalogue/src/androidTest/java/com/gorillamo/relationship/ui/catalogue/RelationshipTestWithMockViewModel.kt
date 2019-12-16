package com.gorillamo.relationship.ui.catalogue

import android.provider.Settings
import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
//import androidx.test.rule.ActivityTestRule
//import com.gorillamo.relationship.abstraction.dto.Relationship
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

//import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.core.context.loadKoinModules
//import org.koin.core.context.stopKoin
//import org.koin.dsl.module
//import org.koin.test.KoinTest
//import org.mockito.Mockito
//import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class RelationshipTestWithMockViewModel {
//class RelationshipTestWithMockViewModel: KoinTest {
    @Suppress("unused")
    private val tag:String = RelationshipTestWithMockViewModel::class.java.name

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

        onView(withText("name 0")).check(matches(isDisplayed()))
    }

    @Test
    fun should_display_last_contacted_in_terms_of_days(){

        val activity = rule.launchActivity(null)

        activity.replaceItems(generateRelationshipList())

        Thread.sleep(5000)

        onView(withText("Today")).check(matches(isDisplayed()))
        onView(withText("Yesterday")).check(matches(isDisplayed()))
        onView(withText("2 days ago")).check(matches(isDisplayed()))
        onView(withText("3 days ago")).check(matches(isDisplayed()))
    }

    private fun generateRelationshipList():List<RelationshipItemAdapter.RelationshipItem>{


        return List(5) { i ->

            val time = System.currentTimeMillis() - oneDayInMillis() * i
            val item = RelationshipItemAdapter.RelationshipItem(
                name = "name $i",
                timeLastContacted = time
            )
            Log.d("$tag generateRelationshipList","$time")
            item
        }
    }

    private fun oneDayInMillis():Long{
        return 1000 * 60 * 60 * 24
    }

}