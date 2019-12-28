package com.gorillamo.relationship.abstraction.dto

interface Relationship{

    val id:Int
    val name:String
    val lastContacted:Long
    val count:Int
    val range:Int
    val ready:Boolean

}