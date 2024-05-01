package jik.inu.data.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jik.inu.data.network.interceptor.provideLoggingInterceptor
import jik.inu.data.network.service.CertificationService
import jik.inu.data.network.service.VideoService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val baseUrl =
        "http://inugram-env-1.eba-qtg2ppft.ap-northeast-2.elasticbeanstalk.com/"
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideCertificationService(retrofit: Retrofit): CertificationService =
        retrofit.create(CertificationService::class.java)

    @Provides
    @Singleton
    fun provideVideoService(retrofit: Retrofit): VideoService =
        retrofit.create(VideoService::class.java)
}