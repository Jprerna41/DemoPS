package com.sapient.recipeapp.utils.extensions

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.TextView
import android.widget.Toast

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Any.toast(context: Context?): Toast {
    return Toast.makeText(context, this.toString(), Toast.LENGTH_LONG).apply { show() }
}

fun TextView.setTextFromHtml(text: String?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT))
    } else {
        setText(Html.fromHtml(text))
    }
}