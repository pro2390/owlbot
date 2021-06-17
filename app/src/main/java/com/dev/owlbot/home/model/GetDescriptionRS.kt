package com.dev.owlbot.home.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//Model class for object returned by definition api
class GetDescriptionRS {
    @SerializedName("definitions")
    @Expose
    var definitions: ArrayList<Definition>? = null

}