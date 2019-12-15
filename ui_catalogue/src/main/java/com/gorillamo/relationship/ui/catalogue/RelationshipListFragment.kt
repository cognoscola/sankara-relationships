package com.gorillamo.relationship.ui.catalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.relationship_list.*
import java.util.ArrayList

class RelationshipListFragment :Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.relationship_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                values = ArrayList()
            )
    }

    public fun updateContent(list:List<RelationshipItemAdapter.RelationshipItem>){
        (relationship_list.adapter as RelationshipItemAdapter).replaceItems(list)
    }

    companion object{

        fun newInstance():RelationshipListFragment{

            return RelationshipListFragment()
        }
    }
}