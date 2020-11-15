package com.example.perfectweather

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.perfectweather.data.ApiWeather
import com.example.perfectweather.ui.dashboard.DialogWindow
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val SELECT_CITY = "select_city"
    var PRIVATE_MODE = 0
    val PREF_NAME = "mindorks-welcome"
    val FAVORITES_SIZE = "favorites_size"
    val FAVORITES_ = "favorites_"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
// Passing each menu ID as a set of Ids because each
// menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //GlobalScope.launch(Dispatchers.Main) {

    }

    /*fun butnClick(view: View) {
        val apiService = ApiWeather()
        GlobalScope.launch(Dispatchers.Main) {
            val UrlModel = apiService.getCurrentWeather(editText.text.toString()).await()
            weatherInfo.text = UrlModel.sys.country.toString()
        }
    }*/

}