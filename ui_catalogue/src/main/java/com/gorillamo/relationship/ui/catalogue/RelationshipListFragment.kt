package com.gorillamo.relationship.ui.catalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.relationship_list.*
import kotlinx.android.synthetic.main.relationship_list_fragment.*
import java.util.ArrayList

class RelationshipListFragment :Fragment(){

    lateinit var relationshipView:RelationshipView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.relationship_list_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(relationship_list)

        nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_today -> {
                    relationshipView.todayClicked()
                    true
                }
                R.id.navigation_all -> {
                    relationshipView.allRelationshipsClicked()
                    true
                }
                else -> false
            }
        }
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
                values = ArrayList()
            )
    }

    public fun updateContent(list:List<RelationshipItemAdapter.RelationshipItem>){
        (relationship_list.adapter as RelationshipItemAdapter).replaceItems(list)
    }

    companion object{

        fun newInstance(view:RelationshipView):RelationshipListFragment{

            return RelationshipListFragment().apply {
                this.relationshipView = view
            }
        }
    }
}