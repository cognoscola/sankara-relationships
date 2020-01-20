package com.gorillamo.details

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.gorillamo.details.data.DetailDao
import com.gorillamo.details.data.DetailDatabaseObject
import org.koin.core.module.Module
import org.koin.dsl.module

//So we have an interesting problem here. Its that we need a database
internal object DetailsModules {

/*    private val databaseModule = module {
        single {
            val app: Application = get()

            Room.databaseBuilder(app, ApplicationDatabase::class.java, NAME).build()
        }
    }*/

    @Suppress("unused")
    private val tag:String = DetailsModules::class.java.name

    private val repository = module{
//        single<DetailsRepository<T>
    }

    private val daoModules = module {
        single<DetailDao> {
            object:DetailDao{
                override suspend fun delete(entityId: String): Int {
                    return 0
                }

                override fun getAllRelationshipList(): List<DetailDatabaseObject> {
                    Log.d("$tag delete","")
                   return emptyList<DetailDatabaseObject>()
                }

                override suspend fun insertOrReplace(item: DetailDatabaseObject): Long {
                   return 0L
                }
            }
        }
    }

    val modules: List<Module> = listOf(daoModules)


}