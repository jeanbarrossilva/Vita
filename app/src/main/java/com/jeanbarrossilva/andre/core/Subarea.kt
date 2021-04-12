package com.jeanbarrossilva.andre.core

import android.content.Context
import java.io.Serializable

open class Subarea(
	context: Context,
	val id: Int,
	var title: String
): Serializable {
	var indicator: SubareaIndicator = SubareaIndicator.Unset(context)
}