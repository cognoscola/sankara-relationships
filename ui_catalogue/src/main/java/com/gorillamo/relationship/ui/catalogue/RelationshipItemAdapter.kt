package com.gorillamo.relationship.ui.catalogue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.relationship_list_content.view.*

class RelationshipItemAdapter(
    private val values: ArrayList<RelationshipItem>
) : RecyclerView.Adapter<RelationshipItemAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->

            /* val intent = Intent(v.context, com.gorillamo.relationship.catalog.RelationshipDetailActivity::class.java).apply {
                    putExtra(com.gorillamo.relationship.catalog.RelationshipDetailFragment.ARG_ITEM_ID, item.id)
                }*/
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
        val name: String?,
        val timeLastContacted: Long?
    )
}
