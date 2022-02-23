package com.picpay.desafio.android.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.repository.UserDataRepository
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val securityModule = module {

    fun provideGson() = GsonBuilder().create()

    fun provideHttpClient() = OkHttpClient.Builder()
        .build()

    fun provideRetrofit(factory: Gson, client: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
        .addConverterFactory(GsonConverterFactory.create(factory))
        .client(client)
        .build()

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}
