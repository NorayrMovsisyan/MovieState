package com.example.moviestate.core.api.di

import com.example.moviestate.BuildConfig
import com.example.moviestate.core.AUTHORIZATION
import com.example.moviestate.core.BEARER
import com.example.moviestate.core.CONNECT_TIMEOUT
import com.example.moviestate.core.CONTENT_TYPE_APPLICATION_JSON
import com.example.moviestate.core.READ_TIMEOUT
import com.example.moviestate.core.api.TmdbApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val apiModule = module {
    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(AUTHORIZATION, "$BEARER ${BuildConfig.ACCESS_TOKEN}")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(logging)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
    single<TmdbApi> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(
                Json { ignoreUnknownKeys = true }
                    .asConverterFactory(CONTENT_TYPE_APPLICATION_JSON.toMediaType())
            )
            .build()
            .create(TmdbApi::class.java)
    }
}