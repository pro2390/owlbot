package com.dev.owlbot.home.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//Model class for definition object returned by api
class Definition {
    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("definition")
    @Expose
    var definition: String? = null

    @SerializedName("example")
    @Expose
    var example: String? = null

    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null

}