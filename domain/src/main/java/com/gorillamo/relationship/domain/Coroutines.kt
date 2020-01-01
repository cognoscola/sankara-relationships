package com.gorillamo.relationship.domain

import kotlinx.coroutines.*

object Coroutines{

    fun <T: Any> ioGivenDispatch(dispatcher:CoroutineDispatcher,work:suspend (() -> T?)): Job =
        CoroutineScope(dispatcher).launch {
            work()
        }


    fun <T: Any> io(work:suspend (() -> T?)): Job =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

    fun <T: Any> ioThenMain(work: suspend (() -> T?), callback: ((T?) -> Unit)? = null): Job =
        CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.IO).async {
                return@async work()
            }.await()
            callback?.let {
                it(data)
            }
        }
}