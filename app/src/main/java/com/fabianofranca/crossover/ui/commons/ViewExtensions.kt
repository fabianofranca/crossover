package com.fabianofranca.crossover.ui.commons

import android.view.View

fun View.toggleVisibility(expression: Boolean, visibility: Int = View.GONE) =
    setVisibility(if (expression) View.VISIBLE else visibility)
