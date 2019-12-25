package com.gorillamo.relationship.abstraction.dto

interface Relationship{

    val id:Int
    val name:String?
    val timeLastContacted:Long?
    val frequency:Float?

}