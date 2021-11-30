package com.dating.mvigeminitutorial.di

import com.dating.mvigeminitutorial.data.NewsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val networkModule = module {
    single { createNetworkClient("https://newsapi.org") }
    single { provideApi(get()) }
}

fun createNetworkClient(baseUrl: String) =
    retrofitClient(baseUrl, httpClient())


private fun httpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
}

fun provideApi(retrofit: Retrofit) = retrofit.create<NewsApi>()

private fun retrofitClient(baseUrl: String, client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
