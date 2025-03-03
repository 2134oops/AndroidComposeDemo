package com.rc.base.koinModule

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rc.base.network.NetworkResponseAdapterFactory
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<ConnectionPool> {
        ConnectionPool()
    }


    single(qualifier = named("OkHttpClient")) {
        OkHttpClient.Builder()
            .connectionPool(get<ConnectionPool>())
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY))
            .build()
    }


}