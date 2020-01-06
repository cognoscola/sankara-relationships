package com.gorillamo.relationship

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class RelationshipScheduler(context:Context,parameters:WorkerParameters)
    :CoroutineWorker(context,parameters){

    override suspend fun doWork(): Result {


        return Result.success()
    }

}