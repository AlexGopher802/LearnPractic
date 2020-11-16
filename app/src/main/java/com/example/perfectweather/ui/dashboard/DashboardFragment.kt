package com.example.perfectweather.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.perfectweather.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    val cityName = arrayOf(
        "Не выбрано",
        "Москва",
        "Санкт-Петербург",
        "Лондон",
        "Канада",
        "Прага",
        "Подольск",
        "Берлин",
        "Париж",
        "Мытищи",
        "Королев",
        "Долгопрудный",
        "Химки",
        "Красногорск",
        "Одинцово"
    )

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mdf = DialogWindow()
        val manager = activity?.supportFragmentManager
        if (manager != null) {
            mdf.show(manager, "myDialog")
        }

    }
}