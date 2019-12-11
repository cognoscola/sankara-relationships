package com.gorillamo.relationship.shared

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

abstract class EntryActivity :AppCompatActivity(){
    @Suppress("unused")
    private val tag:String = EntryActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModules()
        Log.d("$tag onCreate","Loading Modules")
    }

    protected abstract fun loadModules()
}