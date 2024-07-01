package com.dicoding.finalfundamentalssubmission.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.finalfundamentalssubmission.data.local.entity.FavoriteUserEntity

@Dao
interface FavoriteUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteUser: FavoriteUserEntity)

    @Delete
    fun delete(favoriteUser: FavoriteUserEntity)

    @Query ("SELECT COUNT(username) FROM favoritedUsers WHERE username = :username")
    fun selectFavoritedUsers(username: String): LiveData<Int>

    @Query("SELECT * FROM favoritedUsers WHERE username = :username")
    fun getFavoriteUserByUsername(username: String?): LiveData<FavoriteUserEntity>

    @Query("SELECT * FROM favoritedUsers")
    fun getAllFavoriteUsers(): LiveData<List<FavoriteUserEntity>>

}