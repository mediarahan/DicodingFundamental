package com.dicoding.finalfundamentalssubmission.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.finalfundamentalssubmission.viewmodel.DetailViewModel
import com.dicoding.finalfundamentalssubmission.viewmodel.FavoriteUserViewModel
import com.dicoding.finalfundamentalssubmission.viewmodel.MainViewModel
import com.dicoding.finalfundamentalssubmission.viewmodel.SettingViewModel
import com.dicoding.finalfundamentalssubmission.utlis.SettingPreferences

class ViewModelFactory (private val application: Application, private val pref: SettingPreferences) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(application) as T
        }
        if (modelClass.isAssignableFrom(FavoriteUserViewModel::class.java)) {
            return FavoriteUserViewModel(application) as T
        }
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(pref) as T
        }
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(pref) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(application: Application, pref: SettingPreferences): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(application, pref)
            }.also { instance = it }
    }
}