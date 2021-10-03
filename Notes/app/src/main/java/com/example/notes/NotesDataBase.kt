package com.example.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDataBase:RoomDatabase() {
    abstract fun getNotesDao(): NotesDao

    //crete a singleton
    companion object{
        @Volatile
        private var INSTANCE: NotesDataBase? = null

        fun getDatabase(context: Context): NotesDataBase{
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDataBase::class.java,
                    "Notes_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}