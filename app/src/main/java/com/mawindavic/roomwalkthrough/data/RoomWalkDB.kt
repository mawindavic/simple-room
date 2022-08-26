package com.mawindavic.roomwalkthrough.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class RoomWalkDB : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var mInstance: RoomWalkDB? = null

        fun instance(context: Context): RoomWalkDB {
            if (mInstance == null) {
                synchronized(this) {
                    mInstance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomWalkDB::class.java,
                        "room_walk_db"
                    ).build()
                }

            }
            return mInstance!!
        }
    }


}