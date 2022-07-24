package com.fabianofranca.crossover.ui.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.viewbinding.ViewBinding

@Composable
fun <T : ViewBinding> ComposableBindableItem<T>.BindableItemToCompose(modifier: Modifier? = null) {

    AndroidViewBinding(
        factory = { inflater, parent, attachToParent ->
            this.getViewBinding(inflater, parent, attachToParent)
        },
        modifier = modifier ?: Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) { this@BindableItemToCompose.bind(this, 0) }
}
