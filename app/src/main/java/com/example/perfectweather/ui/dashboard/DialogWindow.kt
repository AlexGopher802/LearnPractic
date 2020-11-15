package com.example.perfectweather.ui.dashboard

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import android.R.string
import android.widget.ArrayAdapter



class DialogWindow : DialogFragment(){
    public var pohui : String = ""
    var st: String = ""
    private val cityName = arrayOf("Москва", "Санкт-Петербург", "Екатеринбург", "Сызрань", "Рязань", "Тула", "Подольск", "Балашиха", "Мытищи", "Королев", "Долгопрудный", "Химки", "Красногорск", "Одинцово", "Троицк")
    //private val checkItems = booleanArrayOf(false, false, false)
//val cityName = resources.getStringArray(R.array.ArrayCity)
    val checkItems = booleanArrayOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false)

    override fun onCreateDialog(savedInstanceState: Bundle?) : Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Выбор городов")
                .setMultiChoiceItems(cityName, checkItems){
                        dialog, which, isChecked ->
                    checkItems[which] = isChecked
                    val name = cityName[which]
                    Toast.makeText(activity, name, Toast.LENGTH_LONG).show()
                }
                .setPositiveButton("Готов"){
                        dialog, id ->
                    var count : Int = 0
                    for(i in cityName.indices){
                        if(checkItems[i]){
                            count++
                        }
                    }
                    var citys = arrayOfNulls<String>(count)

                    count = 0
                    for(i in cityName.indices){
                        if(checkItems[i]){
                            citys[count] = i.toString()
                            count++
                        }
                    }


                }
                .setNegativeButton("Отмена"){
                        dialog, _ -> dialog.cancel()
                }
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
        super.onCreate(savedInstanceState)


    }

}