package com.example.perfectweather.ui.home

import android.app.Activity
import android.content.Context
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

        var sharePref = activity?.getSharedPreferences(MainActivity().PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharePref?.edit()

        var citys = Array<String?>(sharePref!!.getInt(MainActivity().FAVORITES_SIZE, 0), {""})
        for(i in 0..citys.size-1){
            citys[i] = sharePref!!.getString(MainActivity().FAVORITES_+(i.toString()), "")
        }

        GlobalScope.launch(Dispatchers.Main) {
            val adapter = ArrayAdapter(
                parentFragment!!.requireContext(),
                android.R.layout.simple_spinner_item,
                citys
            )
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            blood_spinner?.adapter = adapter
        }

        blood_spinner.setSelection(sharePref.getInt(MainActivity().SELECT_CITY, 0))

        blood_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                weatherTemp.text = ""
                weatherDescription.text = "Загрузка..."
                val city : String = parent.getItemAtPosition(position).toString()
                editor?.putInt(MainActivity().SELECT_CITY, position)
                editor?.apply()

                val apiService = ApiWeather()
                GlobalScope.launch(Dispatchers.Main) {
                    try{
                        val Weather = apiService.getCurrentWeather(city).await()
                        val temp : Float = ((Weather.main.temp.toFloat() - 273.15f)*100).toInt().toFloat()/100
                        weatherTemp.text = temp.toString() + " c°"
                        weatherDescription.text = Weather.weather[0].description.toString()
                    }
                    catch (e: Exception){
                        weatherDescription.text = "Ошибка"
                    }
                }

            }
            override fun onNothingSelected(parent: AdapterView<*>){ }
        }
    }
}
