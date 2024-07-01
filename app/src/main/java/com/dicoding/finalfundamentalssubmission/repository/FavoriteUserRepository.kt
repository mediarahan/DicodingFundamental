package com.dicoding.finalfundamentalssubmission.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.finalfundamentalssubmission.data.local.entity.FavoriteUserEntity
import com.dicoding.finalfundamentalssubmission.data.local.room.FavoriteUserDao
import com.dicoding.finalfundamentalssubmission.data.local.room.FavoriteUserDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class FavoriteUserRepository(application: Application) {
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()
    private val mFavoriteDao: FavoriteUserDao

    init {
        val db = FavoriteUserDatabase.getInstance(application)
        mFavoriteDao = db.FavoriteUserDao()
    }

    fun insertFavorite(user: FavoriteUserEntity) {
        executorService.execute{
            mFavoriteDao.insert(user)
        }
    }

    fun deleteFavorite(user: FavoriteUserEntity) {
        executorService.execute{
            mFavoriteDao.delete(user)
        }
    }

    fun getFavoriteUserByUsername(username: String?): LiveData<FavoriteUserEntity> {
        return mFavoriteDao.getFavoriteUserByUsername(username)
    }

    fun selectFavoritedUsers(username: String): LiveData<Int> {
        return mFavoriteDao.selectFavoritedUsers(username)
    }

    fun getAllFavoriteUsers(): LiveData<List<FavoriteUserEntity>> {
        return mFavoriteDao.getAllFavoriteUsers()
    }


}