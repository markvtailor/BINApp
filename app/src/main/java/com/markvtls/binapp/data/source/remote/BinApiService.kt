package com.markvtls.binapp.data.source.remote

import com.markvtls.binapp.data.dto.BinResponseDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

/**Retrofit client for BIN API.*/
interface BinApiService {

    @Headers("Accept-Version: 3")
    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): BinResponseDto


}