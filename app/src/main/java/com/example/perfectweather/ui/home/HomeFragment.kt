package com.example.perfectweather.ui.home

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.perfectweather.MainActivity
import com.example.perfectweather.R
import com.example.perfectweather.data.ApiWeather
import com.example.perfectweather.ui.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeFragment : Fragment() {



    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(this, Observer {
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var sharePref : SharedPreferences = parentFragment!!.requireContext().getSharedPreferences(MainActivity().PREF_NAME, MainActivity().PRIVATE_MODE)
        val editor = sharePref.edit()

        /*var citys = arrayOfNulls<String>(sharePref.getInt(MainActivity().FAVORITES_SIZE, 0))
        if(citys.isEmpty()){
            textView2.text = "Не выбрано"
        }
        else{
            for(i in 0..sharePref.getInt(MainActivity().FAVORITES_SIZE, 0)){
                citys[i] = sharePref.getString(MainActivity().FAVORITES_+i.toString(), "")
            }
        }*/

        val adapter = ArrayAdapter(
            parentFragment!!.requireContext(),
            android.R.layout.simple_spinner_item,
            DashboardFragment().cityName
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        blood_spinner?.adapter = adapter

        blood_spinner.setSelection(sharePref.getInt(MainActivity().SELECT_CITY, 0))


        blood_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                textView2.text = "Загрузка..."
                val city : String = parent.getItemAtPosition(position).toString()
                editor.putInt(MainActivity().SELECT_CITY, position)
                editor.apply()

                if(position == 0){
                    textView2.text = "Не выбрано"
                }
                else{
                    val apiService = ApiWeather()
                    GlobalScope.launch(Dispatchers.Main) {
                        try{
                            val Weather = apiService.getCurrentWeather(city).await()
                            textView2.text = Weather.name.toString() + "   " + (Weather.main.temp.toInt() - 273).toString() + "C°"
                        }
                        catch (e: Exception){
                            textView2.text = "Ошибка"
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>){ }
        }

        /*
        val apiService = ApiWeather()
        GlobalScope.launch(Dispatchers.Main) {
            val Weather = apiService.getCurrentWeather("Moscow").await()
            textView2.text = Weather.name.toString() + "  -  " + (Weather.main.temp.toInt() - 273).toString() + "C°"
        }
         */
    }
}

class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener{
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
            textView2.text = "Hello"
        /*val city : String = parent.getItemAtPosition(pos).toString()
        textView2.text = city*/

        /*val apiService = ApiWeather()
        GlobalScope.launch(Dispatchers.Main) {
            val Weather = apiService.getCurrentWeather("Moscow").await()
            textView2.text = Weather.name.toString() + "  -  " + (Weather.main.temp.toInt() - 273).toString() + "C°"
        }*/
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}

 /*

        val apiService = ApiWeather()

        GlobalScope.launch(Dispatchers.Main) {
            val Weather = apiService.getCurrentWeather("Moscow").await()

            /*text_home1.text = Weather.name.toString()
            text_home2.text = (Weather.main.temp.toFloat() - 273.15).toString() + "C°"*/
        }
    }*/