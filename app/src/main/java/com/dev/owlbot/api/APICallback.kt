package com.dev.owlbot.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//To handle callback of http request. We can get http error code through which we can differentiate the error type.
abstract class APICallback<T> : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        val serverCode = response.raw().code()
        if (response.code() == 401 || response.code() == 403 || serverCode == 500 || serverCode == 501 || serverCode == 502 || serverCode == 503 || serverCode == 504) {
            onServerUnderMaintenance()
        } else if (response.isSuccessful) {
            onResponseSuccess(response.body())
        } else {
            onResponseFailure(response.message())
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onResponseFailure(t.message)
    }

    abstract fun onResponseSuccess(response: T?)
    abstract fun onResponseFailure(errorMessage: String?)
    abstract fun onServerUnderMaintenance()
}