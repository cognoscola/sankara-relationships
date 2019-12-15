package com.gorillamo.relationship.ui.catalogue

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
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

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java, true, false)

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
        // 1. declare mock method
      /*  val MOCK_LIST = generateRelationshipList()
        val MOCK_LIVEDATA = MutableLiveData<List<Relationship>>()
        MOCK_LIVEDATA.value = MOCK_LIST

        Mockito.`when`(mockVm.loadAllRelationships())
            .thenReturn(MOCK_LIVEDATA)*/

        // 2. start activity
        rule.launchActivity(null)

        onView(withId(R.id.fab)).perform(click())

        Thread.sleep(1000)

        // 3. test
        onView(withText("Replace with your own action")).check(matches(isDisplayed()))


    }

/*
    private fun generateRelationshipList():List<Relationship>{

        return List(5){

            object : Relationship {
                override val name: String?
                    get() = "name $it"
                override val timeLastContacted: Long?
                    get() = System.currentTimeMillis()
            }
        }

    }
*/

}