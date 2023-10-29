package com.data.repositories.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.domain.datasource.local.IPreferences
import com.domain.model.currency.CurrencyModel
import com.domain.model.user.UserResponseModel
import com.nativedevps.support.inline.toJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesRepository(
    private val dataStore: DataStore<Preferences>
) : IPreferences {

    override suspend fun updateUser(userResponseModel: UserResponseModel) {
        dataStore.edit {
            it[PREFS_USER] = userResponseModel.toJson() ?: error("user cannot be null")
        }
    }

    override val userModel: Flow<UserResponseModel>
        get() = dataStore.data
            .map { preferences ->
                preferences[PREFS_USER] ?: "".toJson()
            }.map {
                return@map Gson().fromJson(it, object : TypeToken<List<CurrencyModel>>() {}.type)
            }

    companion object {
        private val PREFS_CURRENCIES = stringPreferencesKey("preference_currencies")
        private val PREFS_USER = stringPreferencesKey("preference_user")
    }
}


/*
* Support:
* You can store and retrieve information,
* In case you want to store List/Pojo
* Use {@link <Type> gsonType method} as input type (reduces boiler plate code)
* Use @{@link toJson()?:error("it not be null)} for easily converting to json
*
* ## Example
* Adding Key:
* it[PREFS_SOME_POJO_VALUE] = somePojoModel.toJson() ?: error("user cannot be null")
*
* Retrieving Key:
* preferences[PREFS_SOME_LIST_VALUE] ?: gsonType<String>().toJson() //assuming nullable
*
* * [Bonus]
* Use map option with flow return in case you want initialized gson format
*
* Example:
* dataStore.data
        .map { preferences ->
            ... //Preferences retrieval
        }.map {
            return@map Gson().fromJson(it, gsonTyp<SomePojoModelOrList>()) //converting json to Pojo
        }
*
* */