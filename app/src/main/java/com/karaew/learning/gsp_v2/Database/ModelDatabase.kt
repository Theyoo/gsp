package com.karaew.learning.gsp_v2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.karaew.learning.gsp_v2.Model.ModelEntity

@Database(entities = [ModelEntity::class],version = 1,exportSchema = true)
abstract class ModelDatabase: RoomDatabase() {

    abstract fun shopDao(): ModelDao

    companion object {


        @Volatile
        private var INSTANCE: ModelDatabase? = null

        fun getDatabase(context: Context): ModelDatabase {

            val templInstance = INSTANCE
            if (templInstance != null) {

                return templInstance

            }
            synchronized(lock = this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                   ModelDatabase::class.java,
                    "shop_database"
                ).build()
                INSTANCE = instance
                return instance
            }


        }

    }


}