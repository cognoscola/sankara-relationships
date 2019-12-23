package com.gorillamo.relationship.catalog

import android.app.Activity
//import androidx.test.rule.ActivityTestRule


/**
 * allows our test class to access the injected mock objects
 */
/*
class InjectedBaseActivityTest<T : Activity?>(
    activityClass: Class<T>?,
    initialTouchMode: Boolean,
    launchActivity: Boolean
) :
    ActivityTestRule<T>(activityClass, initialTouchMode, launchActivity) {

    interface BeforeOnCreateCallback {
        fun beforeOnCreate()
    }
    var beforeOnCreateCallback: BeforeOnCreateCallback? = null
//    lateinit var mocks:InjectUtility

//    lateinit var app:App

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()

       */
/* val instrumentation = getInstrumentation()
        app = instrumentation.targetContext.applicationContext as App

        mocks = InjectUtility()

        app.setMockMode(true)
        app.component.inject(mocks)
*//*

        if (beforeOnCreateCallback != null) {
            beforeOnCreateCallback!!.beforeOnCreate()
        }

//        println(instrumentation.targetContext.packageName)
    }

    @org.junit.After
    @Throws(Exception::class)
    fun tearDown() {
//        app.setMockMode(false)
    }
}*/
