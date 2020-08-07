package com.hsb.gyqwanandroid.data.source.remote

import com.hsb.gyqwanandroid.util.loge
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.LoggingEventListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author gyq
 * @date 2020/7/28
 */
object ServiceCreator {
    private const val BASE_URL = "https://www.wanandroid.com/"

    private const val CALL_TIMEOUT = 15L
    private const val CONNECT_TIMEOUT = 15L
    private const val READ_TIMEOUT = 15L
    private const val WRITE_TIMEOUT = 15L

    private val httpClient = OkHttpClient.Builder()
        .callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .eventListenerFactory(LoggingEventListener.Factory())
        .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            loge(it)
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        })

    private val retrofitBuilder = Retrofit.Builder().baseUrl(BASE_URL).client(httpClient.build())
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = retrofitBuilder.build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}