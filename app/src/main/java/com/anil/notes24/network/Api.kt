package com.anil.notes24.network

import android.content.Context
import com.anil.groceries.network.NullOnEmptyConverterFactory
import com.anil.notes24.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

const val NETWORK_TIMEOUT = 30L

//const val BASE_URL = "https://jsonplaceholder.typicode.com/"
const val BASE_URL = "https://dummyjson.com/"


class Api constructor(private val context: Context) {

    val apiService: ApiService by lazy {
        retrofit().create(ApiService::class.java)
    }

    private fun retrofit(): Retrofit {
        return Retrofit.Builder().addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okhttpClient()).addConverterFactory(NullOnEmptyConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(BASE_URL).build()
    }

    private fun okhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        else interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)

        val preferences = context.getSharedPreferences("todos", Context.MODE_PRIVATE)

        return OkHttpClient.Builder().readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS).addInterceptor(interceptor)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request: Request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer ${preferences.getString("token", null)}")
                    .build()

                Timber.d("Headers ${request.headers}")

                chain.proceed(request)
            }).build()
    }

    companion object : SingletonHolder<Api, Context>(::Api)
}