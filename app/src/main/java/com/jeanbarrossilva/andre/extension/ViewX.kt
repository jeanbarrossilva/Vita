package com.jeanbarrossilva.andre.extension

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsCompat.Type.ime

object ViewX {
    val View.rootWindowInsetsCompat
        get() = WindowInsetsCompat.toWindowInsetsCompat(rootWindowInsets)
    
    fun View.setOnSoftInputListener(listener: (isVisible: Boolean) -> Unit) =
        setWindowInsetsAnimationCallback(onEnd = {
            listener(rootWindowInsetsCompat.isVisible(ime()))
        })

    private fun View.setWindowInsetsAnimationCallback(
        onPrepare: (WindowInsetsAnimationCompat) -> Unit = { },
        onStart: (WindowInsetsAnimationCompat, WindowInsetsAnimationCompat.BoundsCompat) -> WindowInsetsAnimationCompat.BoundsCompat = { _, bounds -> bounds },
        onProgress: WindowInsetsCompat.(MutableList<WindowInsetsAnimationCompat>) -> WindowInsetsCompat = { this },
        onEnd: (WindowInsetsAnimationCompat) -> Unit = { }
    ) {
        val callback =
            object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_CONTINUE_ON_SUBTREE) {
                override fun onPrepare(animation: WindowInsetsAnimationCompat) {
                    super.onPrepare(animation)
                    onPrepare(animation)
                }

                override fun onStart(
                    animation: WindowInsetsAnimationCompat,
                    bounds: WindowInsetsAnimationCompat.BoundsCompat
                ): WindowInsetsAnimationCompat.BoundsCompat {
                    super.onStart(animation, bounds)
                    return onStart(animation, bounds)
                }

                override fun onProgress(
                    insets: WindowInsetsCompat,
                    runningAnimations: MutableList<WindowInsetsAnimationCompat>
                ) = onProgress(insets, runningAnimations)

                override fun onEnd(animation: WindowInsetsAnimationCompat) {
                    super.onEnd(animation)
                    onEnd(animation)
                }
            }
        ViewCompat.setWindowInsetsAnimationCallback(this, callback)
    }
}