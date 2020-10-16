package com.training.mvp

class MainPresenter {
    private var view: MainView? = null
    private val manager: MainManager by lazy {
        MainManager()
    }

    fun attachView(view: MainView) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun login(id: String, pwd: String) {
        if (id.isBlank() || pwd.isBlank()) {
            view?.showToast("Error: account/pwd is empty!")
        } else {
            view?.disableButton()
            view?.showLoading()
            manager.login(id, pwd, object : MainManager.MainManagerLister {
                override fun onSuccess(message: String) {
                    view?.apply {
                        showToast(message)
                        enableButton()
                        hideLoading()
                    }
                }

                override fun onFail(errorMessage: String) {
                    view?.apply {
                        showToast(errorMessage)
                        enableButton()
                        hideLoading()
                    }
                }

            })
        }
    }

    fun clear() {
        view?.clear()
    }
}