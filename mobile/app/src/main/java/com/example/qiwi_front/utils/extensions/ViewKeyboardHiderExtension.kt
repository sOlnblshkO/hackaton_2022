package com.example.qiwi_front.utils.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

class ViewKeyboardHiderExtension {
    companion object {
        fun View.hideKeyBoard(){
            val inputMethodManager = context.getSystemService(
                Activity.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
        }
    }
}