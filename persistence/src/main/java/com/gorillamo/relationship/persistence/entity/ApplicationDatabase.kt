package com.gorillamo.relationship.persistence.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gorillamo.relationship.persistence.entity.detail.DetailDao
import com.gorillamo.relationship.persistence.entity.detail.DetailDatabaseObject
import kotlinx.coroutines.CoroutineScope

object DatabaseMetaData {
    const val NAME = "RelationShipData_database"
    const val VERSION = 1

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {

            database.execSQL("CREATE TABLE IF NOT EXISTS `DetailTable` (" +
                    "`id` INTEGER," +
                    "`entityId` INTEGER," +
                    "`timeAdded` INTEGER," +
                    "`description` TEXT," +
                    " PRIMARY KEY(`id`))")
           /* database.execSQL(
                "CREATE TABLE `RelationshipsTable_new` " +
                        "(`id` INTEGER," +
                        " `name` TEXT, " +
                        " `lastContacted` INTEGER, " +
                        " `count` INTEGER, " +
                        " `range` INTEGER, " +
                        " `ready` INTEGER, " +
                    "PRIMARY KEY(`id`))")

            database.execSQL("""
                INSERT INTO RelationshipsTable_new (id, name, lastContacted, count,range,ready)
                SELECT id, name, lastContacted, count,range,ready FROM RelationshipsTable
                """.trimIndent())
            database.execSQL("DROP TABLE RelationshipsTable")
            database.execSQL("ALTER TABLE RelationshipsTable_new RENAME TO RelationshipsTable")*/
        }
    }
}



@Database(entities = [RelationshipDatabaseObject::class,DetailDatabaseObject::class], version = 1, exportSchema = false)
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
                    .addMigrations(DatabaseMetaData.MIGRATION_1_2)
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

