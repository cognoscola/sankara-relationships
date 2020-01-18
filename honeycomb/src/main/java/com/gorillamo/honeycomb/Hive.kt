package com.gorillamo.honeycomb

/**
 * A Hive is a "structure" that helps an app manage its entities.
 */
interface Hive{

    fun addProperty(system:()->Any):Hive



    companion object{
        fun <T> defineInstance():Hive{
            return HiveImpl()
        }
    }

}