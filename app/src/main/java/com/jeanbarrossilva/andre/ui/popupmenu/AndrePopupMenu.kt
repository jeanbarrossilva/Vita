package com.jeanbarrossilva.andre.ui.popupmenu

import android.content.Context
import android.view.View
import android.widget.PopupMenu

class AndrePopupMenu(context: Context, view: View): PopupMenu(context, view) {
	private val onMenuItemClickListeners = mutableListOf<OnMenuItemClickListener>()
	
	fun addOnMenuItemClickListener(listener: OnMenuItemClickListener) =
		onMenuItemClickListeners.add(listener)
	
	init {
		setOnMenuItemClickListener {
			onMenuItemClickListeners.forEach { listener -> listener.onMenuItemClick(it) }
			true
		}
	}
}