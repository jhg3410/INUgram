package jik.inu.data.network.interceptor


import jik.inu.data.datastore.CertificationPreferencesDataSource
import jik.inu.inugram.data.BuildConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject


internal val provideLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
}

class AuthInterceptor @Inject constructor(
    private val preferencesDataSource: CertificationPreferencesDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val accessToken = runBlocking {
            preferencesDataSource.accessToken.first()
        }

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", accessToken)
            .build()

        return chain.proceed(newRequest)
    }
}