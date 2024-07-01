package com.dicoding.finalfundamentalssubmission.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.finalfundamentalssubmission.R

class SplashScreen : AppCompatActivity() {

    companion object {
        private const val delay = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val actionBar = supportActionBar
        actionBar?.hide()

        Handler().postDelayed({
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }, delay.toLong())
    }
}