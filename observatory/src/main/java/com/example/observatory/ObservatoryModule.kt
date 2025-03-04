package com.example.observatory

import com.example.observatory.currentWeatherInfo.usecase.CurrentWeatherInfoUseCase
import com.example.observatory.currentWeatherInfo.usecase.CurrentWeatherInfoUseCaseImpl
import com.example.observatory.currentWeatherInfo.viewModel.CurrentWeatherInfoViewModel
import com.example.observatory.network.ObservatoryApiEndPoint
import com.example.observatory.network.gson.GsonDeserializer
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
            .registerTypeAdapter(Uvindex::class.java, GsonDeserializer<Uvindex>())
            .create()
    }


    factory(qualifier = named("ObservatoryApiEndPoint")) {
        get<Retrofit>(qualifier = named("ObservatoryRetrofit")).create(ObservatoryApiEndPoint::class.java)
    }


    single {
        ObservatoryRepository(get(qualifier = named("ObservatoryApiEndPoint")))
    }

    factory<CurrentWeatherInfoUseCase> {
        CurrentWeatherInfoUseCaseImpl(repository = get())
    }

    factory<CurrentWeatherInfoViewModel> {
        CurrentWeatherInfoViewModel(application = get(), currentWeatherInfoUseCase = get())
    }
}