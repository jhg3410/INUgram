package jik.inu.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jik.inu.data.repository.certification.certification.CertificationRepository
import jik.inu.data.repository.certification.certification.CertificationRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCertificationRepository(
        certificationRepositoryImpl: CertificationRepositoryImpl
    ): CertificationRepository
}