package com.fabianofranca.crossover.ui.commons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.xwray.groupie.viewbinding.BindableItem

abstract class ComposableBindableItem<T : ViewBinding> : BindableItem<T>() {
    fun getViewBinding(inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) = this.initializeViewBinding(inflater.inflate(layout, parent, attachToParent))
}