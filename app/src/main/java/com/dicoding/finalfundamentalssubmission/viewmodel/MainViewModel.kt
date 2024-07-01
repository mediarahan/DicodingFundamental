package com.dicoding.finalfundamentalssubmission.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.dicoding.finalfundamentalssubmission.remote.response.GithubResponse
import com.dicoding.finalfundamentalssubmission.remote.response.ItemsItem
import com.dicoding.finalfundamentalssubmission.remote.retrofit.ApiConfig
import com.dicoding.finalfundamentalssubmission.utlis.SettingPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val pref: SettingPreferences): ViewModel() {

    private val _userdata = MutableLiveData<List<ItemsItem>>()
    val userdata: LiveData<List<ItemsItem>> = _userdata

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        getUserData("arif")
    }

    fun getUserData(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getGithubUserData(username)
        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _userdata.value = response.body()?.items
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }

            }
            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

}