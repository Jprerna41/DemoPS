package com.sapient.recipeapp.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sapient.recipeapp.R

fun ImageView.loadRecipeImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .fitCenter()
        .placeholder(R.drawable.ic_placeholder_food)
        .error(R.drawable.ic_placeholder_food)
        .into(this)
}