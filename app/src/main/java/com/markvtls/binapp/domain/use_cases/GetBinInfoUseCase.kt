package com.markvtls.binapp.domain.use_cases

import com.markvtls.binapp.data.dto.BinResponseDto
import com.markvtls.binapp.domain.model.BinInfo
import com.markvtls.binapp.domain.model.toDomain
import com.markvtls.binapp.domain.repository.BinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetBinInfoUseCase @Inject constructor(
    private val repository: BinRepository
) {
    suspend operator fun invoke(bin: String): Flow<BinInfo> = flow {
        val binInfo = repository.getBinInfo(bin)

        emit(binInfo.toDomain())
    }
}