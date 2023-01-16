package com.quviservicestechnician.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.quviservicestechnician.R
import com.quviservicestechnician.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val userPreferences = UserPreferences(this)
//
//        val userInfo = runBlocking { userPreferences.getUserProfile() }
//        if (userInfo.results.name.toString().isEmpty()) {
//            loadFragment(ServiceFragment())
//        } else {
        val navView: BottomNavigationView = binding.bottomNavigation

//        val navController = Navigation.findNavController(this@HomeActivity, R.id.nav_host_fragment_activity_home) as NavController
        val navController = this.findNavController( R.id.nav_host_fragment_activity_home) as NavController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home_fragment, R.id.nav_booking, R.id.nav_earning, R.id.nav_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        bottomNav = findViewById(R.id.bottom_navigation) as BottomNavigationView
//        bottomNav.setOnNavigationItemReselectedListener {
//
//                when (it.itemId) {
//                    R.id.home -> {
//                        loadFragment(HomeFragment())
//                        return@setOnNavigationItemReselectedListener
//                    }
//                    R.id.booking -> {
//
//                    }
//                    R.id.earning -> {
//
//                    }
//                    R.id.profile -> {
//                        loadFragment(User_Profile_Fragment())
//                        return@setOnNavigationItemReselectedListener
//                    }
//                }
////            }
//        }
    }

//    private  fun loadFragment(fragment: Fragment){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.loginFragmentContainerView,fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
}