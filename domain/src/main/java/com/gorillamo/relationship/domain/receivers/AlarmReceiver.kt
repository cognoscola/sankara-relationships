package com.gorillamo.relationship.domain.receivers

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.domain.Coroutines.ioGivenDispatch
import com.gorillamo.relationship.domain.R

import com.gorillamo.scheduler.Scheduler
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.KoinComponent
import org.koin.core.get
import timber.log.Timber
import java.text.DateFormat
import java.text.SimpleDateFormat

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

    var df: DateFormat = SimpleDateFormat("dd:MM:yy:HH:mm:ss")


    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("$tag onReceive","${intent?.action}")
        
        intent?.let {

            if (intent.hasExtra(KEY_ALARM)) {
            }

            when (it.action) {
                EVENT_WAKEUP -> {

                    ioGivenDispatch(coroutineDispatcher) {
                        with(relationshipRepo.getRelationshipAsync()){

                            Timber.i("Filtering Items: ")
                            this.forEach { relationship ->
                                Timber.i(
                                    "(${relationship.id})${relationship.name} Last: ${df.format(
                                        relationship.lastContacted
                                    )}"
                                )
                            }
                            val list =scheduler.getItemsDue(this)
                            list.forEach{ item ->

                                relationshipRepo.insertOrUpdateRelationship(object :Relationship{
                                    override val count: Int get() = item.count
                                    override val id: Int get() = item.id
                                    override val lastContacted: Long get() = item.lastContacted
                                    override val name: String get() = item.name
                                    override val range: Int get() = item.range
                                    override val ready: Boolean get() = true
                                })
                            }
                        }
                    }

                    //We're going to run a notification just to visually verify that
                    //this went off .
                    with(context.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager){

                        val builder = Notification.Builder(context)
                        builder.setContentTitle("Recent: ${df.format(System.currentTimeMillis())}")
                        builder.setSmallIcon(R.drawable.ic_crop_square_black_24dp)
                        notify("Tag",0,builder.build())

                    }
                }
                else -> {

                }
            }
        }
    }
}