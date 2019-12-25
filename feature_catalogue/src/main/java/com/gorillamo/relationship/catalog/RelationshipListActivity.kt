package com.gorillamo.relationship.catalog

import android.os.Bundle
import android.util.Log

import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.Observer
import com.gorillamo.relationship.abstraction.dto.Relationship

import com.gorillamo.relationship.shared.EntryActivity
import com.gorillamo.relationship.ui.catalogue.RelationshipItemAdapter
import com.gorillamo.relationship.ui.catalogue.RelationshipListFragment
import com.gorillamo.relationship.ui.catalogue.RelationshipView

import kotlinx.android.synthetic.main.activity_relationship_list.*
import org.koin.android.ext.android.inject

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [RelationshipDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class RelationshipListActivity : EntryActivity() {
@Suppress("unused")
private val tag:String = RelationshipListActivity::class.java.name

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private val relationshipViewModel: RelationshipViewModel by inject()
    private val FRAGMENT_TAG = "LIST_FRAGMENT"

    override fun loadModules() {

        CatalogueModule.load()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relationship_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        relationshipViewModel.loadAllRelationships()?.observe(this, Observer { list ->
            list?.let {

                supportFragmentManager.findFragmentByTag(FRAGMENT_TAG)?.let { fragment ->

                    val items = it.map {

                        Log.d("$tag onCreate","Found Item with Id ${it.id}")
                        RelationshipItemAdapter.RelationshipItem(
                            name = it.name?:"",
                            timeLastContacted = it.timeLastContacted?:0L,
                            frequency = 0F
                        )
                    }
                    (fragment as RelationshipListFragment).updateContent(items)
                }
            }
        })

        //Set up the fragment
        supportFragmentManager.beginTransaction()
               .add(R.id.fragmentContainer, RelationshipListFragment.newInstance(relationshipViewModel),FRAGMENT_TAG)
            .commit()

    }
}
