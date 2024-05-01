package jik.inu.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jik.inu.data.repository.certification.certification.CertificationRepository
import jik.inu.data.repository.certification.certification.CertificationRepositoryImpl
import jik.inu.data.repository.certification.video.VideoRepository
import jik.inu.data.repository.certification.video.VideoRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCertificationRepository(
        certificationRepositoryImpl: CertificationRepositoryImpl
    ): CertificationRepository

    @Binds
    fun bindVideoRepository(
        videoRepositoryImpl: VideoRepositoryImpl
    ): VideoRepository
}