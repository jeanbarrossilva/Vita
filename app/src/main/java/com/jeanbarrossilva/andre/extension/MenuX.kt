package com.jeanbarrossilva.andre.extension

import android.graphics.drawable.Drawable
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.children

object MenuX {
	fun Menu.add(
		title: CharSequence,
		icon: Drawable? = null,
		order: Int = children.count(),
		groupId: Int = 0,
		id: Int = order,
		onClick: MenuItem.() -> Unit = { }
	) = add(groupId, id, order, title).also { item ->
		icon?.let { item.icon = it }
		item.setOnMenuItemClickListener {
			onClick(it)
			true
		}
	}
}