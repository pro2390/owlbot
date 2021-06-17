package com.dev.owlbot.home.interactor

import com.dev.owlbot.BuildConfig
import com.dev.owlbot.api.APICallback
import com.dev.owlbot.api.APIServiceUtil.Companion.instance
import com.dev.owlbot.common.Utils.Companion.getHeader
import com.dev.owlbot.home.model.GetDescriptionRS
import retrofit2.Call
//This class is for creating retrofit instance and providing required info for api call
object ConnectionManager {

    fun getDescription(callback: APICallback<GetDescriptionRS?>?, queryText: String?) {
        val callObj: Call<GetDescriptionRS?>? = instance!!.getApiInterface()
            .getDescription(BuildConfig.API_URL+queryText, getHeader())
        callObj!!.enqueue(callback)
    }
}