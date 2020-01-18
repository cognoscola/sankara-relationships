package com.gorillamo.details

import com.gorillamo.definitions.PointInTime
import java.util.*

interface Detail {

    val created: PointInTime
    val detail: String

}
