package com.gorillamo.relationship.ui.catalogue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.relationship_list_content.view.*
import java.util.*
import kotlin.collections.ArrayList

const val DAY_MILLIS = 86400000

class RelationshipItemAdapter(
    private val values: ArrayList<RelationshipItem>,
    private val onItemCheckedCallback:(RelationshipItem)->Any?,
    private val onItemClickCallback:(RelationshipItem)->Any?
) : RecyclerView.Adapter<RelationshipItemAdapter.ViewHolder>() {
    @Suppress("unused")
    private val tag:String = RelationshipItemAdapter::class.java.name

    private val onClickListener: View.OnClickListener
    private val checkClickListener :View.OnClickListener
    private val cal = Calendar.getInstance()


    init {
        onClickListener = View.OnClickListener { v ->
            onItemClickCallback(v.tag as RelationshipItem)
        }
        checkClickListener = View.OnClickListener { v->
            onItemCheckedCallback(v.tag as RelationshipItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.relationship_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.text = item.name
        holder.lastSeen.text = getTimeDifferenceString(item.timeLastContacted, item.ready)

        with(holder.checked) {
            visibility = if(item.ready) View.VISIBLE else View.INVISIBLE
            tag = item
            setOnClickListener(checkClickListener)
        }

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    fun getTimeDifferenceString(time: Long,ready:Boolean):String{

        if ((time == 0L).or(ready) ) {
            return "Catch up!"
        }

        val diff = System.currentTimeMillis() - time
        val day = (diff / (DAY_MILLIS)).toInt()

        return when(day){
            0 -> "Today"
            1 -> "Yesterday"
            else -> "$day days ago"
        }
    }

    fun replaceItems(inList:List<RelationshipItem>){
        this.values.clear()
        inList.forEach { this.values.add(it) }
        notifyDataSetChanged()
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.nameTextView
        val lastSeen: TextView = view.lastContactTextView
        val checked: ImageView = view.updateCheckBox
    }

    data class RelationshipItem(
        var id:Int,
        var name: String,
        var timeLastContacted: Long,
        var ready:Boolean,
        var frequency:Float

    )
}
