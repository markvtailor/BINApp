package com.markvtls.binapp.domain.use_cases

import com.markvtls.binapp.domain.repository.BinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBinHistoryUseCase @Inject constructor(
    private val repository: BinRepository
) {
    operator fun invoke(): Flow<List<String>> = flow {
        repository.getBinHistory().collect {
            emit(it.toList())
        }
    }
}