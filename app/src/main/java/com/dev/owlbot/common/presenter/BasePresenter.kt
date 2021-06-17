package com.dev.owlbot.common.presenter

import com.dev.owlbot.common.view.BaseView


open class BasePresenter protected constructor(view: BaseView?) {
    private val mView: BaseView? = view
    fun showNetworkAlert() {
        mView?.showNoNetworkAlert()
    }

    // Base implementation to handle error. If you need more control, override it.
    fun handleError(errorMsg: String?) {
        mView?.showError(errorMsg)
    }

    fun showServerUnderMaintenance() {
        mView?.showServerUnderMaintenance()
    }

    fun getView(): BaseView? = mView

}