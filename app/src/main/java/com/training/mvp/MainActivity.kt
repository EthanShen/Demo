package com.training.mvp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {
    private val presenter: MainPresenter by lazy {
        MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_submit.setOnClickListener {
            presenter.login(edit_account.text.toString(), edit_pwd.text.toString())
        }

        btn_clear.setOnClickListener {
            presenter.clear()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun clear() {
        edit_account.setText("")
        edit_pwd.setText("")
    }

    override fun showToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun enableButton() {
        btn_submit.isEnabled = true
        btn_clear.isEnabled = true
    }

    override fun disableButton() {
        btn_submit.isEnabled = false
        btn_clear.isEnabled = false
    }
}