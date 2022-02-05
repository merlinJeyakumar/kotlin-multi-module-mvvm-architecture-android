package com.domain.datasources.local

interface IPreferencesDataSource {
    fun getLanguage(): String?
    fun setLanguage(string: String)
}
