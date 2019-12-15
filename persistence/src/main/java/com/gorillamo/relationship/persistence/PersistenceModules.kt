package com.gorillamo.relationship.persistence

import android.app.Application
import androidx.room.Room
import com.gorillamo.relationship.domain.ports.RelationshipDaoPort
import com.gorillamo.relationship.persistence.adapters.RelationshipDaoAdapter
import com.gorillamo.relationship.persistence.entity.ApplicationDatabase
import com.gorillamo.relationship.persistence.entity.DatabaseMetaData.NAME
import org.koin.core.module.Module
import org.koin.dsl.module


object PersistenceModules {

    private val databaseModule = module {
        single {
            val app: Application = get()

            Room.databaseBuilder(app, ApplicationDatabase::class.java, NAME).build()
        }
    }

    private val daoModules = module {
        single { get<ApplicationDatabase>().relationshipDao() }
    }

    private val portsModule = module {
        single<RelationshipDaoPort> {
            RelationshipDaoAdapter(
                get()
            )
        }
//        single<BookDaoPort> { BookDaoAdapter(get()) }
//        single<PreferenceStorage> { SharedPreferenceStorage(androidContext()) }
    }

    val modules: List<Module> = listOf(
        databaseModule,
        daoModules,
        portsModule
    )

}