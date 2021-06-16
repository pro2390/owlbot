package com.dev.owlbot.home.interactor

import android.content.Context
import com.dev.owlbot.BuildConfig
import com.dev.owlbot.api.APICallback
import com.dev.owlbot.api.APIServiceUtil.Companion.instance
import com.dev.owlbot.common.Utils.Companion.getHeader
import com.dev.owlbot.home.model.GetDescriptionRS
import retrofit2.Call

object ConnectionManager {
    fun getDescription(context: Context?, callback: APICallback<GetDescriptionRS?>?, queryText: String?) {
        val callObj: Call<GetDescriptionRS?>? = instance!!.getApiInterface()
            .getDescription(BuildConfig.API_URL+queryText, getHeader())
        callObj!!.enqueue(callback)
    }
}