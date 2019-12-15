package com.gorillamo.relationship.navigation

import android.content.Intent
import com.gorillamo.relationship.navigation.loaders.loadIntentOrNull

object HomeNavigation : DynamicFeature<Intent> {

    private const val HOME = "com.gorillamo.relationship.catalog.RelationshipListActivity"
//    private const val HOME = "com.gorillamo.relationship.ui.catalogue.ItemListActivity"

    override val dynamicStart: Intent?
        get() = HOME.loadIntentOrNull()


}