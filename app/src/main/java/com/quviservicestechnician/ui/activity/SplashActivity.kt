package com.quviservicestechnician.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.quviservicestechnician.R
import com.quviservicestechnician.data.UserPreferences
import com.quviservicestechnician.data.startNewActivity
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            callNextActivity()

        },2000)
    }

    private fun callNextActivity() {
        val userPreferences = UserPreferences(this)

        userPreferences.accessToken.asLiveData().observe(this, Observer { it ->
            if (it==null) {
                startNewActivity(MainActivity::class.java)
            }
            else{
                var userInfo = runBlocking { userPreferences.getUserProfile()}
                if (userInfo.results.name == null){
                    startNewActivity(ProfileActivity::class.java)
                }
                else{
                    startNewActivity(DashboardActivity::class.java)
                }
            }
        })
    }
}