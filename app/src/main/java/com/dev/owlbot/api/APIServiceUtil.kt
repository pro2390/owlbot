package com.dev.owlbot.api

import com.dev.owlbot.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIServiceUtil private constructor(baseUrl: String) {
    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            ClientBuilder.createClient().build()
        )
        .build()

    fun getApiInterface(): APIInterface {
               return mRetrofit.create<APIInterface>(APIInterface::class.java)
    }

    companion object {
        private var sInstance: APIServiceUtil? = null

        @get:Synchronized
        val instance: APIServiceUtil?
            get() {
                if (sInstance == null) {
                    sInstance = APIServiceUtil(BuildConfig.WS_URL)
                }
                return sInstance
            }
    }

}