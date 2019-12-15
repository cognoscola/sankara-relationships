package com.gorillamo.relationship.catalog

import android.content.Intent
import android.os.Bundle

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer

import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.catalog.dummy.DummyContent
import com.gorillamo.relationship.shared.EntryActivity


import kotlinx.android.synthetic.main.activity_relationship_list.*
import kotlinx.android.synthetic.main.relationship_list.*
import kotlinx.android.synthetic.main.relationship_list_content.view.*
import org.koin.android.ext.android.inject

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [RelationshipDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
//class ItemListActivity : AppCompatActivity() {
class ItemListActivity : EntryActivity() {
@Suppress("unused")
private val tag:String = ItemListActivity::class.java.name

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    private val relationshipViewModel: RelationshipViewModel by inject()

    override fun loadModules() {

        CatalogueModule.load()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relationship_list)

        setSupportActionBar(toolbar)
        toolbar.title = title


//        CatalogueModule.load()

        //TODO pass the viewmodel to the adapter
        relationshipViewModel.loadAllRelationships()?.observe(this, Observer {
            it?.let {

                val items = it.map {
                    SimpleItemRecyclerViewAdapter.RelationshipItem(
                        name = it.name,
                        timeLastContacted = it.timeLastContacted
                    )
                }
                (relationship_list.adapter as SimpleItemRecyclerViewAdapter).replaceItems(items)
            }
        })


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Add 10 to DB", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()


//            for(i in 0..10){
                relationshipViewModel.insert(

                    SimpleItemRecyclerViewAdapter.RelationshipItem(
                        "Name 0",
                        System.currentTimeMillis()
                    )
                )
//            }
        }

        delete.setOnClickListener {

            relationshipViewModel.deleteRelationship(
                SimpleItemRecyclerViewAdapter.RelationshipItem(
                    "Name 0",
                    System.currentTimeMillis()
                )
            )
        }

        if (relationship_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(relationship_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter =
            SimpleItemRecyclerViewAdapter(
                this,
                java.util.ArrayList(),
                twoPane
            )
    }

    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: ItemListActivity,
        private val values: ArrayList<RelationshipItem>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as DummyContent.DummyItem
                if (twoPane) {
                    val fragment = com.gorillamo.relationship.catalog.RelationshipDetailFragment()
                        .apply {
                        arguments = Bundle().apply {

                            putString(com.gorillamo.relationship.catalog.RelationshipDetailFragment.ARG_ITEM_ID, item.id)
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.relationship_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, com.gorillamo.relationship.catalog.RelationshipDetailActivity::class.java).apply {
                        putExtra(com.gorillamo.relationship.catalog.RelationshipDetailFragment.ARG_ITEM_ID, item.id)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.relationship_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.name
            holder.contentView.text = "${item.timeLastContacted}"

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

       fun replaceItems(inList:List<RelationshipItem>){
            this.values.clear()
            inList.forEach { this.values.add(it) }
            notifyDataSetChanged()
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.nameTextView
            val contentView: TextView = view.lastContactTextView
        }

        data class RelationshipItem(
            override val name: String?,
            override val timeLastContacted: Long?
        ):Relationship
    }

}
