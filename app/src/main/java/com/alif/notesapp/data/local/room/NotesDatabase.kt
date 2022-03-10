package com.alif.notesapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alif.notesapp.data.local.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        var instance: NotesDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): NotesDatabase {
            if (instance == null){
                synchronized(this){
                    instance = Room.databaseBuilder(
                        context,
                        NotesDatabase::class.java,
                        "notes.db"
                    ).build()
                }
            }
            return instance as NotesDatabase

        }
    }
}