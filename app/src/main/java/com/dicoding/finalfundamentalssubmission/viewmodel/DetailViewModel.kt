package com.dicoding.finalfundamentalssubmission.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.finalfundamentalssubmission.data.local.entity.FavoriteUserEntity
import com.dicoding.finalfundamentalssubmission.remote.response.ItemsItem
import com.dicoding.finalfundamentalssubmission.remote.retrofit.ApiConfig
import com.dicoding.finalfundamentalssubmission.repository.FavoriteUserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel (application: Application): ViewModel() {

    private val _detaileduserdata = MutableLiveData<ItemsItem>()
    val detaileduserdata: LiveData<ItemsItem> = _detaileduserdata

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    //Lanjut Db
    private val mFavoriteUserRepository : FavoriteUserRepository = FavoriteUserRepository(application)

    companion object {
        private const val TAG = "DetailViewModel"
    }

    fun getDetailedUserData(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<ItemsItem> {
            override fun onResponse(
                call: Call<ItemsItem>,
                response: Response<ItemsItem>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detaileduserdata.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ItemsItem>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getFavoriteUserByUsername(username: String?): LiveData<FavoriteUserEntity> {
        return mFavoriteUserRepository.getFavoriteUserByUsername(username)
    }

    fun insertFavorite(user: FavoriteUserEntity) {
        mFavoriteUserRepository.insertFavorite(user)
    }

    fun deleteFavorite(user: FavoriteUserEntity) {
        mFavoriteUserRepository.deleteFavorite(user)
    }

    fun selectFavoritedUsers(user: String): LiveData<Int> {
        return mFavoriteUserRepository.selectFavoritedUsers(user)
    }

}