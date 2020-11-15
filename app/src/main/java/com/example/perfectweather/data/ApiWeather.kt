package com.example.perfectweather.data

import com.example.perfectweather.data.response.Weather
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "f3a29f18c33577eb5fda6fa5e6025449"

//http://api.openweathermap.org/data/2.5/weather?q=Турция&appid=f3a29f18c33577eb5fda6fa5e6025449&lang=ru

interface ApiWeather {

    @GET("weather")
    fun getCurrentWeather(
        @Query("q") location: String
        //@Query("lang") languageCode: String = "en"
    ): Deferred<Weather>

    companion object{
        operator fun invoke(): ApiWeather{
            val requestInterceptor = Interceptor{chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("appid", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiWeather::class.java)
        }
    }
}