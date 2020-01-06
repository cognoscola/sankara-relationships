package com.gorillamo.relationship

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.domain.Coroutines
import com.gorillamo.relationship.domain.R
import com.gorillamo.scheduler.Scheduler
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.KoinComponent
import org.koin.core.get
import java.text.DateFormat
import java.text.SimpleDateFormat

class RelationshipScheduler(context:Context,parameters:WorkerParameters)
    :CoroutineWorker(context,parameters), KoinComponent{

    val relationshipRepo: RelationshipRepository = get()
    val scheduler: Scheduler<Relationship> = get()
//    val coroutineDispatcher: CoroutineDispatcher = get()
    var df: DateFormat = SimpleDateFormat("dd:MM:yy:HH:mm:ss")

    override suspend fun doWork(): Result {


        with(relationshipRepo.getRelationshipAsync()) {

            timber.log.Timber.i("Filtering Items: ")
            this.forEach { relationship ->
                timber.log.Timber.i(
                    "(${relationship.id})${relationship.name} Last: ${df.format(
                        relationship.lastContacted
                    )}"
                )
            }
            val list = scheduler.getItemsDue(this)
            list.forEach { item ->

                relationshipRepo.insertOrUpdateRelationship(object : Relationship {
                    override val count: Int get() = item.count
                    override val id: Int get() = item.id
                    override val lastContacted: Long get() = item.lastContacted
                    override val name: String get() = item.name
                    override val range: Int get() = item.range
                    override val ready: Boolean get() = true
                })
            }
        }
        //We're going to run a notification just to visually verify that
        //this went off .
        with(applicationContext.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager) {

            val builder = Notification.Builder(applicationContext)
            builder.setContentTitle("WorkManager: ")
            builder.setContentText("${df.format(System.currentTimeMillis())}")
            builder.setSmallIcon(R.drawable.ic_crop_square_black_24dp)
            notify("Tag", 0, builder.build())

        }

        return Result.success()
    }

}