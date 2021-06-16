package com.dev.owlbot.common.presenter

import com.dev.owlbot.common.view.BaseView


open class BasePresenter protected constructor(view: BaseView?) {
    private val mView: BaseView?
    fun showNetworkAlert() {
        if (null != mView) {
            mView.showNoNetworkAlert()
        }
    }

    // Base implementation to handle error. If you need more control, override it.
    fun handleError(errorMsg: String?) {
        if (mView != null) {
            mView.showError(errorMsg)
        }
    }

    fun showServerUnderMaintenance() {
        if (null != mView) {
            mView.showServerUnderMaintenance()
        }
    }

    fun getView(): BaseView? = mView

    init {
        mView = view
    }
}