package com.loci.room_crud.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.loci.room_crud.db.dao.NumberDao
import com.loci.room_crud.db.entity.NumberEntity

@Database(entities = [NumberEntity::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun numberDao(): NumberDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(
            context: Context
        ): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "number_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}