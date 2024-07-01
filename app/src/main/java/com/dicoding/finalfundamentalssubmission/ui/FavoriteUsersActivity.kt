package com.dicoding.finalfundamentalssubmission.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.finalfundamentalssubmission.data.local.entity.FavoriteUserEntity
import com.dicoding.finalfundamentalssubmission.databinding.ActivityFavoriteUsersBinding
import com.dicoding.finalfundamentalssubmission.remote.response.ItemsItem
import com.dicoding.finalfundamentalssubmission.viewmodel.FavoriteUserViewModel
import com.dicoding.finalfundamentalssubmission.utlis.SettingPreferences
import com.dicoding.finalfundamentalssubmission.utlis.dataStore

class FavoriteUsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val favoriteUserViewModel = ViewModelProvider(this, ViewModelFactory(application,pref))[FavoriteUserViewModel::class.java]

        val layoutManager = LinearLayoutManager(this)
        binding.rvUserdata.layoutManager = layoutManager

        favoriteUserViewModel.getAllFavoriteUsers().observe(this) {users ->
            val adapter = UserListAdapter()
            val items = mapFavoriteUsers(users)
            adapter.submitList(items)
            binding.rvUserdata.adapter = adapter
        }
    }

    private fun mapFavoriteUsers(users: List<FavoriteUserEntity>): List<ItemsItem> {
        val items = mutableListOf<ItemsItem>()
        users.forEach { user ->
            val item = ItemsItem(
                login = user.username,
                avatarUrl = user.avatarUrl,
                followers = 0,
                following = 0,
                name = ""
            )
            items.add(item)
        }
        return items
    }
}