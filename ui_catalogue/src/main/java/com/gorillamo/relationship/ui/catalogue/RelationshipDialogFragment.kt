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
import kotlinx.android.synthetic.main.fragment_relationship_dialog.*


class RelationshipDialogFragment : DialogFragment() {

    lateinit var interactionCallback:ItemDialogInteraction

    //TODO app crashes on screen rotation of this view
    private var name:String = ""
    private var frequency: Float = 0F

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v: View = inflater.inflate(R.layout.fragment_relationship_dialog, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameEditText.hint = "John Snow "
        nameEditText.setText(name)

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
            .add(R.id.frequencyPickerContainer,FrequencyPickerFragment.newInstance {
                frequency = it
            })
            .commit()


        saveButton.setOnClickListener {
            interactionCallback.saveClicked(nameEditText.text.toString(),frequency)
            dismiss()
        }

        deleteButton.setOnClickListener {

            interactionCallback.deleteClicked(nameEditText.text.toString())
            dismiss()
        }

    }



    companion object {
        fun newInstance(callback:ItemDialogInteraction): RelationshipDialogFragment {
            return RelationshipDialogFragment().apply {
                this.interactionCallback = callback
            }
        }


        fun newInstance(callback:ItemDialogInteraction,name:String,frequency: Float): RelationshipDialogFragment {
            return RelationshipDialogFragment().apply {
                this.interactionCallback = callback
                this.name = name
                this.frequency = frequency
            }
        }
    }

    interface ItemDialogInteraction{
        fun saveClicked(name:String,frequency:Float)
        fun deleteClicked(name:String)
    }
}