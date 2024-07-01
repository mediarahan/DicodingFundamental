package com.dicoding.finalfundamentalssubmission.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.finalfundamentalssubmission.data.local.entity.FavoriteUserEntity
import com.dicoding.finalfundamentalssubmission.repository.FavoriteUserRepository

class FavoriteUserViewModel (application: Application): ViewModel(){

    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)

    fun getAllFavoriteUsers(): LiveData<List<FavoriteUserEntity>> {
        return mFavoriteUserRepository.getAllFavoriteUsers()
    }

}