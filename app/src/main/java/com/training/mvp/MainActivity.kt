package com.training.mvp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_submit.setOnClickListener {
            login(edit_account.text.toString(), edit_pwd.text.toString())
        }

        btn_clear.setOnClickListener {
            clear()
        }
    }

    private fun login(id: String, pwd: String) {
        if (id.isBlank() || pwd.isBlank()) {
            showToast("Error: account/pwd is empty!")
        } else {
            disableButton()
            showLoading()

            LoginApi().login(id, pwd, object : LoginApi.Listener {
                override fun onSuccess(message: String) {
                    showToast(message)
                    enableButton()
                    hideLoading()
                }

                override fun onFail(errorMessage: String) {
                    showToast(errorMessage)
                    enableButton()
                    hideLoading()
                }
            })
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    private fun clear() {
        edit_account.setText("")
        edit_pwd.setText("")
    }

    private fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progress.visibility = View.GONE
    }

    private fun enableButton() {
        btn_submit.isEnabled = true
        btn_clear.isEnabled = true
    }

    private fun disableButton() {
        btn_submit.isEnabled = false
        btn_clear.isEnabled = false
    }
}