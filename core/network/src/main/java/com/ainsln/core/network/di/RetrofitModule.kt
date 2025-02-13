package com.ainsln.core.network.di

import com.ainsln.core.network.BuildConfig
import com.ainsln.core.network.retrofit.RetrofitApi
import com.ainsln.core.network.utils.SessionInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitModule {

    @Provides
    @Singleton
    fun provideJson() = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: SessionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(
        json: Json,
        client: OkHttpClient
    ): RetrofitApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .client(client)
            .build()
            .create(RetrofitApi::class.java)
    }

}
