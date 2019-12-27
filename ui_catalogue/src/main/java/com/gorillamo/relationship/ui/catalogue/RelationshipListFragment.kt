package com.gorillamo.relationship.ui.catalogue

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.relationship_list.*
import kotlinx.android.synthetic.main.fragment_relationship_list.*
import java.util.ArrayList

class RelationshipListFragment :Fragment(){

    lateinit var relationshipView:RelationshipView


    val dialogInteraction = object :RelationshipDialogFragment.ItemDialogInteraction{
        override fun deleteClicked(name: String) {
            relationshipView.deleteClicked(name)
        }

        override fun saveClicked(name: String, frequency: Float) {
            relationshipView.addClicked(name,frequency)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_relationship_list,container,false)
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

        addFab.setOnClickListener {

            RelationshipDialogFragment.newInstance(dialogInteraction).show(fragmentManager!!.beginTransaction(),"Dialog!")
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        recyclerView.adapter =
            RelationshipItemAdapter(values = ArrayList()){

                RelationshipDialogFragment.newInstance(dialogInteraction, it.name,it.frequency).show(fragmentManager!!.beginTransaction(),"Dialog!")

            }
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