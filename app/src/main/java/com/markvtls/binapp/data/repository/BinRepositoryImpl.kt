package com.markvtls.binapp.data.repository

import com.markvtls.binapp.data.dto.BinResponseDto
import com.markvtls.binapp.data.source.local.BinDataStore
import com.markvtls.binapp.data.source.remote.BinApiService
import com.markvtls.binapp.domain.repository.BinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BinRepositoryImpl @Inject constructor(
    private val api: BinApiService,
    private val dataStore: BinDataStore
): BinRepository {
    override suspend fun getBinInfo(bin: String): BinResponseDto {
        return api.getBinInfo(bin)
    }

    override suspend fun saveBinToHistory(bins: Set<String>) {
       dataStore.saveBinToHistoryList(bins)
    }

    override suspend fun getBinHistory(): Flow<Set<String>> {
        return dataStore.binListFlow
    }
}