package com.gorillamo.relationship.abstraction.dto

interface Relationship{

    val id:Int
    val name:String
    val lastContacted:Long
    val frequency:Float
    val ready:Boolean

}