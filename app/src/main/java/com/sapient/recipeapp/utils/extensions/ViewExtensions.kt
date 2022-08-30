package com.sapient.recipeapp.utils.extensions

import android.content.Context
import android.view.View
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