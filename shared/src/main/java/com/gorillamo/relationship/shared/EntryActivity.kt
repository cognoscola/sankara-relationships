package com.gorillamo.relationship.shared

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class EntryActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModules()
    }

    protected abstract fun loadModules()
}