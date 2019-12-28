package com.gorillamo.relationship.catalog

import android.os.Bundle

import androidx.lifecycle.Observer
import com.gorillamo.relationship.abstraction.dto.Relationship

import com.gorillamo.relationship.shared.EntryActivity
import com.gorillamo.relationship.ui.catalogue.RelationshipItemAdapter
import com.gorillamo.relationship.ui.catalogue.RelationshipListFragment

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

        relationshipViewModel.data()?.observe(this, Observer { list -> updateIfPossible(list) })

        //Set up the fragment
        supportFragmentManager.beginTransaction()
               .add(R.id.fragmentContainer, RelationshipListFragment.newInstance(relationshipViewModel),FRAGMENT_TAG)
            .commit()

    }

    private fun updateIfPossible(input:List<Relationship>?){
        input?.let {
            supportFragmentManager.findFragmentByTag(FRAGMENT_TAG)?.let { fragment ->
                (fragment as RelationshipListFragment).updateContent(convertToAdapterItem(input))
            }
        }
    }

    private fun convertToAdapterItem(input:List<Relationship>):List<RelationshipItemAdapter.RelationshipItem>{
      return input.map {
          RelationshipItemAdapter.RelationshipItem(
              id = it.id,
              name = it.name,
              timeLastContacted = it.lastContacted,
              ready =  it.ready,
              count = it.count,
              range = it.range
          )
      }
    }
}
