package com.markvtls.binapp.di

import android.content.Context
import com.markvtls.binapp.data.repository.BinRepositoryImpl
import com.markvtls.binapp.data.source.local.BinDataStore
import com.markvtls.binapp.data.source.remote.BinApiService
import com.markvtls.binapp.domain.repository.BinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideBinRepository(api: BinApiService, dataStore: BinDataStore): BinRepository =
        BinRepositoryImpl(api, dataStore)

    @Provides
    @Singleton
    fun provideBinDataStore(@ApplicationContext context: Context): BinDataStore = BinDataStore(context)

}