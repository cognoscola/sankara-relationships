package com.gorillamo.relationship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.gorillamo.relationship.navigation.HomeNavigation

class LauncherActivity : AppCompatActivity() {

    @Suppress("unused")
    private val tag:String = LauncherActivity::class.java.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureDynamiFeature()

        HomeNavigation.dynamicStart?.let { startActivity(it) }
        finish()
    }

    private fun configureDynamiFeature() {

    }

    private fun installRegistrationModule() {
       val  splitInstallManager = SplitInstallManagerFactory.create(applicationContext)
        val request = SplitInstallRequest.newBuilder()
            .addModule("registration")
            .build()

        splitInstallManager.startInstall(request)
            .addOnSuccessListener {
                Log.d(tag, it.toString())}
            .addOnFailureListener {
                Log.e(tag, it.toString())}
    }
}
