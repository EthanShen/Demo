package com.training.mvp

import android.os.Handler
import android.os.Looper

class LoginApi {
    private val handler by lazy { Handler(Looper.getMainLooper()) }

    fun login(id: String, pwd: String, listener: Listener?) {
        handler.postDelayed({
            if (id == "kkbox" && pwd == "123456") {
                listener?.onSuccess("Login Success :)")
            } else {
                listener?.onFail("Error: account/pwd is incorrect!")
            }
        }, 2000)
    }

    interface Listener {
        fun onSuccess(message: String)
        fun onFail(errorMessage: String)
    }
}