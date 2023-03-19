package com.example.raczakupsecond.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.data.utils.ApplicationPreferences
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_RacZakupSecond)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //для SharedPreferences
        ApplicationPreferences.setup(applicationContext)

        navController = this.findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationBar.setupWithNavController(navController)

//        setupNavigation()

//        navController = supportFragmentManager.findFragmentById(R.id.place_holder)!!
//            .findNavController()
//
//        binding.bottomNavigationBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.checkCodeFragment, R.id.startFragment -> {
                    binding.bottomNavigationBar.visibility = View.GONE
                    binding.root.setBackgroundResource(R.drawable.background)
                }
                else -> {
                    binding.root.setBackgroundResource(R.color.white)
                    binding.bottomNavigationBar.visibility = View.VISIBLE
//                    binding.bottomNavigationBar.menu.getItem(0).isChecked = true
                }
            }
        }

//        binding.bottomNavigationBar.setOnItemSelectedListener {
//
//            when (it.itemId) {
//                R.id.menu_main -> {
//
//                }
//                R.id.menu_packs -> {
//
//                }
//                R.id.menu_kart -> {
//
//                }
//                R.id.menu_profile -> {
//
//                }
//            }
//            true
//
//        }
    }

//    private fun setupNavigation() {
//        navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.place_holder) as NavHostFragment
//        navController = navHostFragment.navController
//
//        binding.bottomNavigationBar.setupWithNavController(navController)
//
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//    }
}