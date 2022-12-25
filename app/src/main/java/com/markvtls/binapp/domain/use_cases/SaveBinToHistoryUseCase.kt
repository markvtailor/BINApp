package com.markvtls.binapp.domain.use_cases

import com.markvtls.binapp.domain.repository.BinRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SaveBinToHistoryUseCase @Inject constructor(
    private val repository: BinRepository
) {
    suspend operator fun invoke(bin: String) {
        repository.getBinHistory().collect { currentHistory ->
            val newHistory = mutableSetOf(bin)
            newHistory.addAll(currentHistory)
            repository.saveBinToHistory(newHistory)
        }
    }
}