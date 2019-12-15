package com.gorillamo.relationship.ui.catalogue

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_list_tester.*


import kotlinx.android.synthetic.main.relationship_list.*
import java.util.ArrayList

class CatalogueTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_tester)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        setupRecyclerView(relationship_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        val list = ArrayList<RelationshipItemAdapter.RelationshipItem>()
        for(i in 0..9){
            list.add (RelationshipItemAdapter.RelationshipItem(
                name = "name $i",
                timeLastContacted = System.currentTimeMillis()
            ))
        }
        recyclerView.adapter =
            RelationshipItemAdapter(
                values = list
            )
    }

}
