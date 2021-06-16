package com.dev.owlbot.common.view

interface BaseView {
    fun showProgressBar()
    fun hideProgressBar()
    fun showNoNetworkAlert()
    fun showError(errorMessage: String?)
    fun showServerUnderMaintenance()
}