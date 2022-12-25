package com.markvtls.binapp.domain.repository

import com.markvtls.binapp.data.dto.BinResponseDto
import kotlinx.coroutines.flow.Flow

interface BinRepository {

    suspend fun getBinInfo(bin: String): BinResponseDto

    suspend fun saveBinToHistory(bins: Set<String>)

    suspend fun getBinHistory(): Flow<Set<String>>
}