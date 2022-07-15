package com.example.giphytest.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View?.show() {
    this?.visibility = View.VISIBLE
}
 fun View?.hide() {
    this?.visibility = View.GONE
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}