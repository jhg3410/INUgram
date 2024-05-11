package jik.inu.data.datastore.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private const val CERTIFICATION_DATASTORE_NAME = "CERTIFICATION_PREFERENCES"
    private val Context.certificationDataStore by preferencesDataStore(name = CERTIFICATION_DATASTORE_NAME)

    @Provides
    @Singleton
    @Named("certification")
    fun provideCertificationDataStore(
        @ApplicationContext context: Context,
    ) = context.certificationDataStore
}