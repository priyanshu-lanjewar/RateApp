package com.priyanshu.rateapp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.database.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var submit: Button

    lateinit var nDialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            nDialog = ProgressDialog.show(this@MainActivity, "The RateApp", "Loading", true);
            val navView: BottomNavigationView = findViewById(R.id.nav_view)

            val navController = findNavController(R.id.nav_host_fragment)
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_home,
                    R.id.navigation_history,
                    R.id.navigation_settings
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
            nDialog.dismiss()
        }
        catch (e:Exception)
        {

        }



    }
}
