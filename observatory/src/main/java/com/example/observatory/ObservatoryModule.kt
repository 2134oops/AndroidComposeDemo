package com.example.observatory

import com.example.observatory.currentWeatherInfo.viewModel.CurrentWeatherInfoViewModel
import com.example.observatory.network.ObservatoryApiEndPoint
import com.example.observatory.network.gson.GsonDeserializar
import com.example.observatory.network.model.Uvindex
import com.example.observatory.repository.ObservatoryRepository
import com.google.gson.GsonBuilder
import com.rc.base.network.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val ObservatoryModule = module {

    factory(qualifier = named("GsonBuilder")) {
        GsonBuilder()
            .registerTypeAdapter(Uvindex::class.java, GsonDeserializar())
            .create()
    }

    factory(qualifier = named("ObservatoryRetrofit")) {
        Retrofit.Builder()
            .baseUrl("https://data.weather.gov.hk/")
            .addConverterFactory(GsonConverterFactory.create(get(named("GsonBuilder"))))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .client(get<OkHttpClient>(qualifier = named("OkHttpClient")))
            .build()
    }

    factory(qualifier = named("ObservatoryApiEndPoint")) {
        get<Retrofit>(qualifier = named("ObservatoryRetrofit")).create(ObservatoryApiEndPoint::class.java)
    }


    single {
        ObservatoryRepository(get(qualifier = named("ObservatoryApiEndPoint")))
    }

    factory<CurrentWeatherInfoViewModel> {
        CurrentWeatherInfoViewModel(application = get(), repository = get())
    }
}