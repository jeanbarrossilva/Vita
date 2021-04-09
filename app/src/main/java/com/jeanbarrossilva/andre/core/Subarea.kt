package com.jeanbarrossilva.andre.core

import android.content.Context
import androidx.annotation.StringRes
import com.jeanbarrossilva.andre.R
import java.io.Serializable
import java.util.*

open class Subarea(
	context: Context,
	var title: String
): Serializable {
	val id = UUID.randomUUID()
	var indicator: SubareaIndicator = SubareaIndicator.Unset(context)
	
	constructor(
		context: Context,
		@StringRes titleRes: Int
	): this(context, context.getString(titleRes))

	class Health(context: Context): Subarea(context, R.string.Subarea_name_health)

	class Lounge(context: Context): Subarea(context, R.string.Subarea_name_lounge)

	class Spirituality(context: Context): Subarea(context, R.string.Subarea_name_spirituality)

	class Emotional(context: Context): Subarea(context, R.string.Subarea_name_emotional)

	class Happiness(context: Context): Subarea(context, R.string.Subarea_name_happiness)

	class Intellect(context: Context): Subarea(context, R.string.Subarea_name_intellect)

	class Career(context: Context): Subarea(context, R.string.Subarea_name_career)

	class Contribution(context: Context): Subarea(context, R.string.Subarea_name_contribution)

	class Finances(context: Context): Subarea(context, R.string.Subarea_name_finances)

	class Family(context: Context): Subarea(context, R.string.Subarea_name_family)

	class Love(context: Context): Subarea(context, R.string.Subarea_name_love)

	class Social(context: Context): Subarea(context, R.string.Subarea_name_social)
	
	companion object {
		fun empty(context: Context?) = Subarea(context!!, "")
	}
}