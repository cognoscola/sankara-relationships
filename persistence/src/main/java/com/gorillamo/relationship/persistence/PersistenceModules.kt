package com.gorillamo.relationship.persistence

import android.app.Application
import androidx.room.Room
import com.gorillamo.honeycomb.Hive
import com.gorillamo.relationship.domain.dto.Relationship
import com.gorillamo.relationship.domain.extPorts.DetailRepository
import com.gorillamo.relationship.domain.extPorts.RelationshipRepository
import com.gorillamo.relationship.domain.ports.RelationshipDaoPort
import com.gorillamo.relationship.persistence.adapters.RelationshipDaoAdapter
import com.gorillamo.relationship.persistence.entity.ApplicationDatabase
import com.gorillamo.relationship.persistence.entity.DatabaseMetaData.NAME
import org.koin.core.module.Module
import org.koin.dsl.module


object PersistenceModules {

    private val repositoryModule = module {

        single<RelationshipRepository>{
            RelationshipRepositoryAdapter(get())
        }
    }

    private val detailModule = module{
        single<DetailRepository<Relationship>>{
            DetailRepositoryAdapter(get())
        }
    }

    private val databaseModule = module {
        single {
            val app: Application = get()
            Room.databaseBuilder(app, ApplicationDatabase::class.java, NAME).build()
        }
    }

    private val daoModules = module {
        single { get<ApplicationDatabase>().relationshipDao() }
        single { get<ApplicationDatabase>().detailDao()}
    }

    private val portsModule = module {
        single<RelationshipDaoPort> {
            RelationshipDaoAdapter(
                get() //Dao
            )
        }
    }

    val modules: List<Module> = listOf(
        repositoryModule,
        detailModule,
        databaseModule,
        daoModules,
        portsModule
    )

}