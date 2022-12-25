package com.markvtls.binapp.di

import com.markvtls.binapp.domain.repository.BinRepository
import com.markvtls.binapp.domain.use_cases.GetBinHistoryUseCase
import com.markvtls.binapp.domain.use_cases.GetBinInfoUseCase
import com.markvtls.binapp.domain.use_cases.SaveBinToHistoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetBinInfoUseCase(repository: BinRepository) =
        GetBinInfoUseCase(repository)

    @Provides
    fun provideGetBinHistoryUseCase(repository: BinRepository) =
        GetBinHistoryUseCase(repository)

    @Provides
    fun provideSaveBinToHistoryUseCase(repository: BinRepository) =
        SaveBinToHistoryUseCase(repository)
}