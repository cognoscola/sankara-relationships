package com.gorillamo.relationship.ui.catalogue

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.gorillamo.pickers.FrequencyPickerFragment
import com.gorillamo.relationship.ui.catalogue.RelationshipItemAdapter.*
import kotlinx.android.synthetic.main.fragment_relationship_dialog.*


class RelationshipDialogFragment : DialogFragment() {

    lateinit var interactionCallback:ItemDialogInteraction

    private lateinit var item: RelationshipItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v: View = inflater.inflate(R.layout.fragment_relationship_dialog, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameEditText.hint = "John Snow "
        nameEditText.setText(item.name)

        //TODO write test for text change behaviour
        nameEditText.addTextChangedListener(object:TextWatcher{

            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    deleteButton.visibility = View.INVISIBLE
                }else{
                    deleteButton.visibility = View.VISIBLE
                }
            }
        })

        childFragmentManager.beginTransaction()
            .add(R.id.frequencyPickerContainer,FrequencyPickerFragment.newInstance(item.count,item.range) { count, range ->
                item.count = count
                item.range = range
            })
            .commit()

        saveButton.setOnClickListener {
            item.name = nameEditText.text.toString()
            interactionCallback.saveClicked(item)
            dismiss()
        }

        deleteButton.setOnClickListener {

            interactionCallback.deleteClicked(nameEditText.text.toString())
            dismiss()
        }
    }

    companion object {
        fun newInstance(
            callback: ItemDialogInteraction, item: RelationshipItem =
                RelationshipItem(
                    id = 0,
                    name = "",
                    timeLastContacted = 0,
                    count =  1,
                    range = 1,
                    ready = true
                )
        ): RelationshipDialogFragment {
            return RelationshipDialogFragment().apply {
                this.interactionCallback = callback
                this.item = item
            }
        }
    }

    interface ItemDialogInteraction{
        fun saveClicked(item: RelationshipItem)
        fun deleteClicked(name:String)
    }
}