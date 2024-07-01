package com.dicoding.finalfundamentalssubmission.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.finalfundamentalssubmission.data.local.entity.FavoriteUserEntity

@Database(entities = [FavoriteUserEntity::class], version = 1, exportSchema = false)
abstract class FavoriteUserDatabase : RoomDatabase() {
    abstract fun FavoriteUserDao(): FavoriteUserDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteUserDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): FavoriteUserDatabase {
            return if (INSTANCE != null) {
                INSTANCE as FavoriteUserDatabase
            } else {
                val instance: FavoriteUserDatabase =
                    synchronized(FavoriteUserDatabase::class.java) {
                        androidx.room.Room.databaseBuilder(
                            context.applicationContext,
                            FavoriteUserDatabase::class.java,
                            "favUserDatabase"
                        )
                            .build()
                    }
                INSTANCE = instance
                instance
            }
        }
    }
}
