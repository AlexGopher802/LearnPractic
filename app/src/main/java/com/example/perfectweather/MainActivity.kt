package com.example.perfectweather

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


        GlobalScope.launch(Dispatchers.Main) {
            val cityName = arrayOf(
                "Москва",
                "Санкт-Петербург",
                "Екатеринбург",
                "Сызрань",
                "Рязань",
                "Тула",
                "Подольск",
                "Балашиха",
                "Мытищи",
                "Королев",
                "Долгопрудный",
                "Химки",
                "Красногорск",
                "Одинцово",
                "Троицк"
            )

            val adapter = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                cityName
            )
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            blood_spinner?.adapter = adapter;
        }


        //val favCitiesInput = Array(15, {"Москва"; "Санкт-Петербург"; "Екатеринбург"; "Сызрань"; "Рязань"; "Тула"; "Подольск"; "Балашиха"; "Мытищи"; "Королев"; "Долгопрудный"; "Химки"; "Красногорск"; "Одинцово"; "Троицк"})

        //val phones: Array<String> = arrayOf("Galaxy S8", "iPhone X", "Motorola C350")

        /*val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            cityName
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        blood_spinner?.adapter = adapter;*/


    }

    /*fun butnClick(view: View) {
        val apiService = ApiWeather()
        GlobalScope.launch(Dispatchers.Main) {
            val UrlModel = apiService.getCurrentWeather(editText.text.toString()).await()
            weatherInfo.text = UrlModel.sys.country.toString()
        }
    }*/


}