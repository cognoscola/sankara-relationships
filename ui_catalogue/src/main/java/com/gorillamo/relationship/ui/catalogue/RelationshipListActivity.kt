package com.gorillamo.relationship.ui.catalogue

import android.os.Bundle
import android.os.SystemClock
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.relationship_list.*
import java.util.ArrayList

class RelationshipListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relationship_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        setupRecyclerView(relationship_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        val list = ArrayList<SimpleItemRecyclerViewAdapter.RelationshipItem>()
        for(i in 0..9){
            list.add (SimpleItemRecyclerViewAdapter.RelationshipItem(
                name = "name $i",
                timeLastContacted = System.currentTimeMillis()
            ))
        }
        recyclerView.adapter =
            SimpleItemRecyclerViewAdapter(
                values = list
            )
    }

}
