package com.training.mvp

class MainManager {

    fun login(id: String, pwd: String, listener: MainManagerLister?) {
        LoginApi().login(id, pwd, object : LoginApi.Listener {
            override fun onSuccess(message: String) {
                listener?.onSuccess(message)
            }

            override fun onFail(errorMessage: String) {
                listener?.onFail(errorMessage)
            }
        })
    }

    interface MainManagerLister {
        fun onSuccess(message: String)
        fun onFail(errorMessage: String)
    }
}