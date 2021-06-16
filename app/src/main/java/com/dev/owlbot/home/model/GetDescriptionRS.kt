package com.dev.owlbot.home.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetDescriptionRS {
    @SerializedName("definitions")
    @Expose
    var definitions: ArrayList<Definition>? = null

    @SerializedName("word")
    @Expose
    var word: String? = null

    @SerializedName("pronunciation")
    @Expose
    var pronunciation: String? = null
}