package com.dev.owlbot.home.view

import com.dev.owlbot.common.view.BaseView
import com.dev.owlbot.home.model.GetDescriptionRS

interface GetDescriptionView : BaseView {
    fun onGetDescriptionSuccess(description: GetDescriptionRS?)
}