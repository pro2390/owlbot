package com.dev.owlbot.api

import com.dev.owlbot.home.model.GetDescriptionRS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Url
//retrofit - api endpoint
interface APIInterface {
    @GET
    fun getDescription(
        @Url url: String,
        @HeaderMap headers: HashMap<String, String>?
    ): Call<GetDescriptionRS?>?
}