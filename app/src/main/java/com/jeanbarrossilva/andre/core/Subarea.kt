package com.jeanbarrossilva.andre.core

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jeanbarrossilva.andre.R
import java.io.Serializable

sealed class Subarea(
	@DrawableRes val iconRes: Int,
	val title: String,
	@ColorInt val color: Int,
	open var indicator: SubareaIndicator = SubareaIndicator.Unset
): Serializable {
	constructor(
		context: Context,
		@DrawableRes iconRes: Int,
		@ColorInt color: Int,
		@StringRes titleRes: Int
	): this(iconRes, context.getString(titleRes), color)

	class Health(context: Context):
		Subarea(context, R.drawable.ic_health_and_safety, Color.parseColor("#450EE3"), R.string.Subarea_name_health)

	class Lounge(context: Context):
		Subarea(context, R.drawable.ic_golf_course, Color.parseColor("#128FD2"), R.string.Subarea_name_lounge)

	class Spirituality(context: Context):
		Subarea(context, R.drawable.ic_hiking, Color.parseColor("#E890B8"), R.string.Subarea_name_spirituality)

	class Emotional(context: Context):
		Subarea(context, R.drawable.ic_mood_bad, Color.parseColor("#F88484"), R.string.Subarea_name_emotional)

	class Happiness(context: Context):
		Subarea(context, R.drawable.ic_mood, Color.parseColor("#675DCD"), R.string.Subarea_name_happiness)

	class Intellect(context: Context):
		Subarea(context, R.drawable.ic_psychology, Color.parseColor("#F48C45"), R.string.Subarea_name_intellect)

	class Career(context: Context):
		Subarea(context, R.drawable.ic_work, Color.parseColor("#0674B4"), R.string.Subarea_name_career)

	class Contribution(context: Context):
		Subarea(context, R.drawable.ic_thumb_up, Color.parseColor("#8A5A44"), R.string.Subarea_name_contribution)

	class Finances(context: Context):
		Subarea(context, R.drawable.ic_savings, Color.parseColor("#AACC00"), R.string.Subarea_name_finances)

	class Family(context: Context):
		Subarea(context, R.drawable.ic_family_restroom, Color.parseColor("#F08080"), R.string.Subarea_name_family)

	class Love(context: Context):
		Subarea(context, R.drawable.ic_favorite, Color.parseColor("#2F3E46"), R.string.Subarea_name_love)

	class Social(context: Context):
		Subarea(context, R.drawable.ic_groups, Color.parseColor("#FF9505"), R.string.Subarea_name_social)
}