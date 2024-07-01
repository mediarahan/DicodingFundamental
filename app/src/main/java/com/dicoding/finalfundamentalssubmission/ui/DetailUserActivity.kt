package com.dicoding.finalfundamentalssubmission.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.finalfundamentalssubmission.R
import com.dicoding.finalfundamentalssubmission.data.local.entity.FavoriteUserEntity
import com.dicoding.finalfundamentalssubmission.databinding.ActivityDetailUserBinding
import com.dicoding.finalfundamentalssubmission.remote.response.ItemsItem
import com.dicoding.finalfundamentalssubmission.viewmodel.DetailViewModel
import com.dicoding.finalfundamentalssubmission.utlis.SettingPreferences
import com.dicoding.finalfundamentalssubmission.utlis.dataStore
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
        private const val TAG = "DetailUserActivity"
    }

    private lateinit var binding: ActivityDetailUserBinding

    private var favoriteUserEntity: FavoriteUserEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val detailViewModel = ViewModelProvider(this, ViewModelFactory(application,pref))[DetailViewModel::class.java]

        val username = intent.getStringExtra("username")
        val avatar = intent.getStringExtra("avatar")

        //ViewModel Observe Stuff
        detailViewModel.detaileduserdata.observe(this) { detailUserData ->
            setDetailedUserData(detailUserData)
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        if (username != null) {
            detailViewModel.getDetailedUserData(username)
        }

        if (avatar != null) {
            detailViewModel.getDetailedUserData(avatar)
        }

        // TabLayout
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        if (username != null) {
            sectionsPagerAdapter.username = username
        }
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        //FAB STUFF
        favoriteUserEntity = username?.let {FavoriteUserEntity(it,avatar)}

        detailViewModel.selectFavoritedUsers(username.toString()).observe(this) { isFavorited ->
            val isUserFavorited = isFavorited == 1

            binding.btnLove.setOnClickListener {
                if (isUserFavorited) {
                    binding.btnLove.setImageResource(R.drawable.ic_favorite)
                    detailViewModel.deleteFavorite(favoriteUserEntity as FavoriteUserEntity)
                } else {
                    binding.btnLove.setImageResource(R.drawable.ic_favorite_border)
                    detailViewModel.insertFavorite(favoriteUserEntity as FavoriteUserEntity)
                    Log.d(TAG, "Favorite user inserted successfully: ${favoriteUserEntity!!.avatarUrl}")
                }
            }
        }

        //Observe udah di like atau belum
        detailViewModel.getFavoriteUserByUsername(favoriteUserEntity?.username).observe(this@DetailUserActivity) {user ->
            if(user != null)
                binding.btnLove.setImageResource(R.drawable.ic_favorite)
            else
                binding.btnLove.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun setDetailedUserData(detailedUsersData: ItemsItem) {
        binding.tvName.text = detailedUsersData.name
        binding.tvUsername.text = detailedUsersData.login
        binding.tvFollowers.text =
            String.format(getString(R.string.followers), detailedUsersData.followers)
        binding.tvFollowing.text =
            String.format(getString(R.string.following), detailedUsersData.following)
        Glide.with(this)
            .load(detailedUsersData.avatarUrl)
            .into(binding.IvAvatar)

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}