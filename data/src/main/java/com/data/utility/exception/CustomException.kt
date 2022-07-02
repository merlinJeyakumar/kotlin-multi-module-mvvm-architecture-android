package com.data.utility.exception


import com.domain.model.ErrorModel
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

class CustomException(exception: Throwable) : Throwable() {
    private var exception: Throwable
    private var errorBody: String? = null

    init {
        when (exception) {
            is HttpException -> {
                errorBody = exception.response()?.errorBody()?.string()
            }
        }
        this.exception = exception
    }

    fun getError(): String {
        return when (exception) {
            is HttpException -> getHttpExceptionResponse()?.errorMessage ?: "undefined error"
            is IOException -> "No internet connection"
            else -> exception.localizedMessage ?: "uncaught error"
        }
    }

    fun getCode(): Int? {
        return if (exception is HttpException) {
            getHttpExceptionResponse()?.errorCode
        } else {
            null
        }
    }

    private fun getHttpExceptionResponse(): ErrorModel? {
        return try {
            Gson().fromJson(errorBody, ErrorModel::class.java) ?: ErrorModel(400, "undefined issue")
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}