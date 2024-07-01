package com.dicoding.finalfundamentalssubmission.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.finalfundamentalssubmission.R
import com.dicoding.finalfundamentalssubmission.databinding.ActivityMainBinding
import com.dicoding.finalfundamentalssubmission.remote.response.ItemsItem
import com.dicoding.finalfundamentalssubmission.viewmodel.MainViewModel
import com.dicoding.finalfundamentalssubmission.utlis.SettingPreferences
import com.dicoding.finalfundamentalssubmission.utlis.dataStore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(application,pref))[MainViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        mainViewModel.userdata.observe(this) { usersData ->
            if (usersData.isNullOrEmpty()) {
                Toast.makeText(this, "No results", Toast.LENGTH_SHORT).show()
            } else {
                setUserData(usersData)
            }
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvUserdata.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUserdata.addItemDecoration(itemDecoration)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    mainViewModel.getUserData(searchView.text.toString())
                    false
                }
            searchBar.inflateMenu(R.menu.fav_menu)
            searchBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.to_favorite -> {
                        val intent = Intent(this@MainActivity,FavoriteUsersActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.to_settings -> {
                        val intent = Intent(this@MainActivity, SettingsActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
        }
        mainViewModel.getThemeSettings().observe(this@MainActivity) { themeStatus ->
            if (themeStatus)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun setUserData(usersData: List<ItemsItem>) {
        val adapter = UserListAdapter()
        adapter.submitList(usersData)
        binding.rvUserdata.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}