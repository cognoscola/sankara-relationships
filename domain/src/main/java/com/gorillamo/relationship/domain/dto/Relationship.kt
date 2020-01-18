package com.gorillamo.relationship.domain.dto

import com.gorillamo.details.Detail

//@Entity
interface Relationship{

    //base - things likely to be unique to this app
    val id:Int
    val name:String

    //This is schedulable
    val lastContacted:Long
    val count:Int
    val range:Int
    val ready:Boolean

    //The detail annotation processor comes from Details Library.
    //It will let Hive know that it must store details

    //Details
//    val detail:Detail

}