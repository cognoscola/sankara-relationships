package com.gorillamo.relationship.persistence

import android.app.Application
import androidx.room.Room
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.DetailRepository
import com.gorillamo.relationship.domain.ports.RelationshipDaoPort
import com.gorillamo.relationship.persistence.adapters.DetailRepositoryAdapter
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

    private val detailModule = module {
        single<DetailRepository<Relationship>> {
            DetailRepositoryAdapter(get())
        }
    }

    private val daoModules = module {
        single { get<ApplicationDatabase>().relationshipDao() }
        single { get<ApplicationDatabase>().detailDao()}
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
        detailModule ,
        daoModules,
        portsModule
    )

}