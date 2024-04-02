package jik.inu.data.network.interceptor


import jik.inu.inugram.data.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor


internal val provideLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
}