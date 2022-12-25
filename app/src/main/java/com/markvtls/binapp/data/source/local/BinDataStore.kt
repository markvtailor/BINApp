package com.markvtls.binapp.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val HISTORY = "search_history"
private val BIN_LIST = stringSetPreferencesKey("bin_list")


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = HISTORY
)

class BinDataStore(
    private val context: Context) {


    suspend fun saveBinToHistoryList(bins: Set<String>) {
        context.dataStore.edit { preferences ->
            preferences[BIN_LIST] = bins
        }
    }

    val binListFlow: Flow<Set<String>> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else throw it
        }
        .map { preferences ->
            preferences[BIN_LIST] ?: setOf()
        }

}