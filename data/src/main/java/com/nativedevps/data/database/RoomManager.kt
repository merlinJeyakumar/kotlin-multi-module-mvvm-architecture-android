package com.nativedevps.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nativedevps.data.database.Migration.MIGRATION_v1_2
import com.nativedevps.data.database.dao.flickr.SampleItemDao
import com.nativedevps.domain.entity.flickr.SampleEntity
import nativedevps.data.BuildConfig

@Database(
    entities = [SampleEntity::class],
    version = BuildConfig.DB_VERSION,
    exportSchema = false
)
abstract class RoomManager : RoomDatabase() {
    /**
     * Connects the database to the DAO.
     */
    abstract val sampleItemDao: SampleItemDao

    /*
    * [synchronized]
    * Multiple threads can ask for the database at the same time, ensure we only initialize
    * it once by using synchronized. Only one thread may enter a synchronized block at a
    * time.
    *
    * ${instance}
    * Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
    * Smart cast is only available to local variables.
    *
    * [Migrations]
    * Wipes and rebuilds instead of migrating if no Migration object.
    * Migration is not part of this lesson. You can learn more about
    * migration with Room in this blog post:
    * https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
    *
    * Created by Jeyakumar@01092020
    */
    companion object {
        @Volatile
        private var INSTANCE: RoomManager? = null
        private var DATABASE_NAME = "JK_WANTED"

        fun getInstance(context: Context): RoomManager {
            synchronized(this) {

                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        RoomManager::class.java, DATABASE_NAME)
                        .addMigrations()
                        .fallbackToDestructiveMigration()
                        .addMigrations(MIGRATION_v1_2)
                        .allowMainThreadQueries()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }

                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}