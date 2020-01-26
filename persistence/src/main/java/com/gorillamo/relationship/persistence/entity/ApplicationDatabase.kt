package com.gorillamo.relationship.persistence.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope

object DatabaseMetaData {
    const val NAME = "RelationShipData_database"
    const val VERSION = 1
}

@Database(entities = [RelationshipDatabaseObject::class, DetailDatabaseObject::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase :RoomDatabase() {

    abstract fun relationshipDao(): RelationshipDao
    abstract fun detailDao(): DetailDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationDatabase::class.java,
                    "RelationShipData_database"
                )
//                        .addCallback(TaskDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }


        class TaskDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
/*
            INSTANCE?.let {
                scope.launch(Dispatchers.IO) {
                    //STUFF is happening when we open
                  */
/*
                    wordDao.deleteAll()
                    var word = Word("Hello")
                    wordDao.insert(word)
                    word = Word("World!")
                    wordDao.insert(word)
 *//*
                }
            }
*/
            }
        }
    }
}

