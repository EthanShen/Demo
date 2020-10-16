package com.training.mvp

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun enableButton()
    fun disableButton()
    fun showToast(text: String)
    fun clear()
}