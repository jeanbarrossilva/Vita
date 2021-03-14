package com.jeanbarrossilva.andre.core

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.jeanbarrossilva.andre.R

sealed class Subarea(
	val icon: ImageVector,
	val title: String,
	open var indicator: SubareaIndicator = SubareaIndicator.Unset
) {
	constructor(context: Context, icon: ImageVector, @StringRes titleRes: Int):
		this(icon, context.getString(titleRes))

	class Health(context: Context):
		Subarea(context, Icons.Rounded.HealthAndSafety, R.string.Subarea_name_health)

	class Lounge(context: Context):
		Subarea(context, Icons.Rounded.GolfCourse, R.string.Subarea_name_lounge)

	class Spirituality(context: Context):
		Subarea(context, Icons.Rounded.Hiking, R.string.Subarea_name_spirituality)

	class Emotional(context: Context):
		Subarea(context, Icons.Rounded.MoodBad, R.string.Subarea_name_emotional)

	class Happiness(context: Context):
		Subarea(context, Icons.Rounded.Mood, R.string.Subarea_name_happiness)

	class Intellect(context: Context):
		Subarea(context, Icons.Rounded.Psychology, R.string.Subarea_name_intellect)

	class Career(context: Context):
		Subarea(context, Icons.Rounded.Architecture, R.string.Subarea_name_career)

	class Contribution(context: Context):
		Subarea(context, Icons.Rounded.ThumbUp, R.string.Subarea_name_contribution)

	class Finances(context: Context):
		Subarea(context, Icons.Rounded.Savings, R.string.Subarea_name_finances)

	class Family(context: Context):
		Subarea(context, Icons.Rounded.FamilyRestroom, R.string.Subarea_name_family)

	class Love(context: Context):
		Subarea(context, Icons.Rounded.Favorite, R.string.Subarea_name_love)

	class Social(context: Context):
		Subarea(context, Icons.Rounded.Group, R.string.Subarea_name_social)

	companion object {
		/**
		 * Returns a list of [Subarea]s that doesn't have an [SubareaIndicator.Unset] [indicator].
		 **/
		val List<Subarea>.indicative
			get() = filter { subarea -> subarea.indicator !is SubareaIndicator.Unset }
	}
}