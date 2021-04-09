package com.jeanbarrossilva.andre.extension

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.children
import kotlin.reflect.KClass

object ViewX {
    private val View.window get() = (context as? Activity)?.window
    
    val View.rootWindowInsetsCompat
        get() = WindowInsetsCompat.toWindowInsetsCompat(rootWindowInsets)
    val View.windowInsetsControllerCompat
        get() = window?.let { WindowInsetsControllerCompat(it, this) }
    
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
    
    private fun View.updateLayoutParams(update: ViewGroup.LayoutParams.() -> Unit) {
        layoutParams?.apply(update) ?: run {
            layoutParams = ViewGroup.LayoutParams(width, height)
            updateLayoutParams(update)
        }
    }
    
    @Suppress("UNCHECKED_CAST")
    @PublishedApi
    internal fun <V: View> View.searchFor(isInclusive: Boolean, viewClass: KClass<V>): V? {
        if (this::class == viewClass && isInclusive)
            return this as V
        else if (this is ViewGroup)
            children.forEach { child ->
                if (child::class == viewClass)
                    return child as V
                else if (child is ViewGroup)
                    child.searchFor(isInclusive = false, viewClass)
            }
    
        return null
    }
    
    inline fun <reified V: View> View.searchFor(isInclusive: Boolean = true) =
        searchFor(isInclusive, V::class)
    
    @Suppress("UNCHECKED_CAST")
    fun <V: View> V.setOnClickListener(listener: (V) -> Unit) {
        setOnClickListener { listener(it as V) }
    }
    
    fun View.setOnSoftInputListener(listener: (isVisible: Boolean) -> Unit) =
        setWindowInsetsAnimationCallback(onEnd = {
            listener(rootWindowInsetsCompat.isVisible(ime()))
        })
    
    fun View.setSize(size: Int) =
        updateLayoutParams {
            width = size
            height = size
        }
}