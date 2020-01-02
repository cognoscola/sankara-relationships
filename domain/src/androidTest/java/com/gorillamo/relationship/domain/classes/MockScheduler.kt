package com.gorillamo.relationship.domain.classes

import android.content.Context
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.scheduler.Identifier
import com.gorillamo.scheduler.Scheduler
import com.gorillamo.scheduler.Task

class MockScheduler :Scheduler<Relationship>{
    override fun getItemsDue(input: List<Relationship>): List<Relationship> {
        return generateReadyRelationshipList(5)
    }

    override fun startScheduling(context: Context, tasks: List<Task>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopScheduling(context: Context, identifier: Identifier) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun generateReadyRelationshipList(count:Int):List<Relationship>{

        return List(count){

            object :Relationship{
                override val id: Int get() = it
                override val name: String get() = "name $it"
                override val lastContacted: Long get() = System.currentTimeMillis()
                override val count: Int get() = 1
                override val range: Int get() = 1
                override val ready: Boolean get() = true
            }
        }

    }

}