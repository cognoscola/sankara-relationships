package com.gorillamo.app.relationship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gorillamo.relationship.navigation.HomeNavigation

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeNavigation.dynamicStart?.let { startActivity(it) }
        finish()
    }
}
