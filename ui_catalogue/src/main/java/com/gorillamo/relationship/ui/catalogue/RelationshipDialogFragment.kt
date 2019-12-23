package com.gorillamo.relationship.ui.catalogue




import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment

import kotlinx.android.synthetic.main.fragment_relationship_dialog.*


class RelationshipDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v: View = inflater.inflate(R.layout.fragment_relationship_dialog, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameEditText.hint = "John Snow "
        daysEditText.hint = "3"
        saveButton.setOnClickListener {
            dismiss()
        }
        deleteButton.setOnClickListener {
            dismiss()
        }

    }

    companion object {
        fun newInstance(): RelationshipDialogFragment {
            return RelationshipDialogFragment()
        }
    }
}