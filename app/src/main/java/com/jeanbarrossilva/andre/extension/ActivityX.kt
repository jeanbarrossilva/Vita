package com.jeanbarrossilva.andre.extension

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import androidx.appcompat.widget.ContentFrameLayout
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jeanbarrossilva.andre.extension.ViewX.searchFor
import com.jeanbarrossilva.andre.extension.ViewX.windowInsetsControllerCompat

object ActivityX {
	private val Activity.content
		get() = window?.findViewById<ContentFrameLayout>(android.R.id.content)
	private val Activity.view
		get() = content?.children?.first() ?: content
	
	fun Activity.closeKeyboard() {
		if (Build.VERSION.SDK_INT >= 30)
			view?.windowInsetsController?.hide(WindowInsets.Type.ime())
		else
			view?.windowInsetsControllerCompat?.hide(WindowInsetsCompat.Type.ime())
	}
	
	fun Activity.withFab(block: FloatingActionButton.() -> Unit) {
		view?.searchFor<FloatingActionButton>()?.apply(block)
	}
}