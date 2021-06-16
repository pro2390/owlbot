package com.dev.owlbot.home.presenter

import android.content.Context
import android.widget.Toast
import com.dev.owlbot.api.APICallback
import com.dev.owlbot.common.Utils
import com.dev.owlbot.common.presenter.BasePresenter
import com.dev.owlbot.common.view.BaseView
import com.dev.owlbot.home.interactor.ConnectionManager
import com.dev.owlbot.home.model.GetDescriptionRS
import com.dev.owlbot.home.view.GetDescriptionView

class GetDescriptionPresenter(view: BaseView?) : BasePresenter(view) {
    fun getCityDistrictPinCodeOutPutCall(context: Context?, queryText: String?) {

        if (Utils.isNetworkAvailable(context) == false) {
            showNetworkAlert()
            return
        }
        ConnectionManager.getDescription(
            context,
            object : APICallback<GetDescriptionRS?>() {
                override fun onResponseSuccess(response: GetDescriptionRS?) {
                    val view: GetDescriptionView = getView() as GetDescriptionView? ?: return
                    view?.onGetDescriptionSuccess(response)
                }

                override fun onResponseFailure(errorMessage: String?) {
                    handleError(errorMessage)
                }

                override fun onServerUnderMaintenance() {
                    showServerUnderMaintenance()
                }
            },
            queryText
        )
    }
}