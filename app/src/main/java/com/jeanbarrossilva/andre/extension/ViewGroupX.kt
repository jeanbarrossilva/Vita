package com.jeanbarrossilva.andre.extension

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

object ViewGroupX {
	fun ViewGroup.chaining(isInclusive: Boolean = true, block: View.() -> Unit) {
		if (isInclusive)
			apply(block)
		children.forEach { child ->
			child.apply(block)
			if (child is ViewGroup)
				child.chaining(block = block)
		}
	}
}