package com.gorillamo.relationship.domain.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.domain.Coroutines.io
import com.gorillamo.relationship.domain.Coroutines.ioGivenDispatch
import com.gorillamo.scheduler.Scheduler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.get
import java.util.*

class AlarmReceiver:BroadcastReceiver(),KoinComponent{

    @Suppress("unused")
    private val tag:String = AlarmReceiver::class.java.name
    companion object {

        const val EVENT_WAKEUP  = "com.gorillamoa.routines.event.wakeup"
        const val EVENT_SLEEP = "com.gorillamoa.routines.event.sleep"
        const val KEY_ALARM = "A"
        const val WAKE_UP_INTENT_CODE = 1
        const val SLEEP_INTENT_CODE =2

    }

    val relationshipRepo:RelationshipRepository = get()
    val scheduler:Scheduler<Relationship> = get()
    val coroutineDispatcher:CoroutineDispatcher = get()

    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("$tag onReceive","${intent?.action}")
        
        intent?.let {

            if (intent.hasExtra(KEY_ALARM)) {
            }

            when (it.action) {
                EVENT_WAKEUP -> {

                    relationshipRepo.getRelationshipsLive().value?.let {
                        val readyList = scheduler.getItemsDue(it)
                        ioGivenDispatch(coroutineDispatcher) {
                            readyList.forEach {
                                relationshipRepo.insertOrUpdateRelationship(it)
                            }
                        }
                    }
                }
                else -> {

                }
            }
        }
    }
}