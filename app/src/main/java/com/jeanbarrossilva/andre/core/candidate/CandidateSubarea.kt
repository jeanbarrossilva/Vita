package com.jeanbarrossilva.andre.core.candidate

import android.content.Context
import com.jeanbarrossilva.andre.core.SubareaIndicator
import com.jeanbarrossilva.andre.database.subarea.SubareaEntity

data class CandidateSubarea(
	private val context: Context,
	var title: String = "",
	var indicator: SubareaIndicator = SubareaIndicator.Unset(context)
) {
	constructor(context: Context, titleRes: Int): this(context, context.getString(titleRes))
	
	fun createEntity(context: Context, areaId: Int) =
		SubareaEntity(areaId, title, indicator.level(context))
}