package com.jeanbarrossilva.andre.extension

import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jeanbarrossilva.andre.core.Area
import com.jeanbarrossilva.andre.core.CheckableColorStateList
import com.jeanbarrossilva.andre.extension.ContextX.colorOf
import com.jeanbarrossilva.andre.extension.MenuX.add

object BottomNavigationViewX {
	fun BottomNavigationView.setupWithAreas(areas: List<Area>, onSelectArea: (Area) -> Unit) {
		areas.forEach { area ->
			menu.add(area.title, ContextCompat.getDrawable(context, area.iconRes)!!)
		}
		setOnNavigationItemSelectedListener { item ->
			val selectedArea = areas[item.order]
			val colorStateList =
				CheckableColorStateList(
					uncheckedColor = context colorOf android.R.attr.colorControlNormal,
					checkedColor = selectedArea.color
				)
			
			itemIconTintList = colorStateList
			itemTextColor = colorStateList
			onSelectArea(selectedArea)
			Log.d(
				"BottomNavigationViewX.setupWithAreas",
				"Selected area: ${selectedArea::class.simpleName}"
			)
			true
		}
		menu.performIdentifierAction(0, 0)
	}
}