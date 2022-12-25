package com.markvtls.binapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markvtls.binapp.data.dto.BinResponseDto
import com.markvtls.binapp.domain.model.BinInfo
import com.markvtls.binapp.domain.repository.BinRepository
import com.markvtls.binapp.domain.use_cases.GetBinHistoryUseCase
import com.markvtls.binapp.domain.use_cases.GetBinInfoUseCase
import com.markvtls.binapp.domain.use_cases.SaveBinToHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankViewModel @Inject constructor(
    private val getBinInfo: GetBinInfoUseCase,
    private val getBinHistory: GetBinHistoryUseCase,
    private val saveBinToHistory: SaveBinToHistoryUseCase
): ViewModel() {

    private var _binInfo: MutableLiveData<BinInfo> = MutableLiveData()
    val binInfo get() = _binInfo

    private var _binHistory: MutableLiveData<List<String>> = MutableLiveData()
    val binHistory get() = _binHistory

    init {
        getHistory()
    }

    fun getInfo(bin: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getBinInfo(bin).collect {
                    _binInfo.postValue(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    private fun getHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            getBinHistory().collect {
                println(it)
                _binHistory.postValue(it)
            }
        }
    }

    fun saveToHistory(bin: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveBinToHistory(bin)
        }
    }
}