package com.gorillamo.relationship.ui.catalogue

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_list_tester.*
import kotlinx.android.synthetic.main.relationship_list.*
import java.util.ArrayList

class CatalogueTestActivity : AppCompatActivity() {

    //TODO when you come back we'll provide a modelview mockito with Koin
    lateinit var fragment:RelationshipListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_tester)
        setSupportActionBar(toolbar)

        fragment = RelationshipListFragment.newInstance(MockViewModel())
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragment,"TAG")
            .commit()
    }

    fun replaceItems(inList:List<RelationshipItemAdapter.RelationshipItem>){
        runOnUiThread {
            fragment.updateContent(inList)
        }
    }

    inner class MockViewModel():RelationshipView{
        override fun allRelationshipsClicked() {

        }

        override fun todayClicked() {

        }

        override fun addClicked(name: String, frequency: Float) {

            val list = List<RelationshipItemAdapter.RelationshipItem>(1){
                RelationshipItemAdapter.RelationshipItem(
                    name,0,true,1.0f
                )
            }
            fragment.updateContent(list)
        }

        override fun deleteClicked(name: String) {

            val list = List<RelationshipItemAdapter.RelationshipItem>(1){
                RelationshipItemAdapter.RelationshipItem(
                    name+"1",0,true,1.0f
                )
            }
            fragment.updateContent(list)
        }
    }
}
