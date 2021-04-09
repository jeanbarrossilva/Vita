package com.jeanbarrossilva.andre.core

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import com.jeanbarrossilva.andre.R
import java.io.Serializable

@Suppress("Unused")
open class Area(
	val title: String,
	@ColorInt val color: Int,
	val subareas: List<Subarea>
): Serializable {
	constructor(
		context: Context,
		@StringRes titleRes: Int,
		@ColorInt color: Int,
		subareas: List<Subarea>
	): this(
		context.getString(titleRes),
		color,
		subareas
	)
	
	class LifeQuality(context: Context):
		Area(
			context,
			R.string.Area_name_life_quality,
			Color.parseColor("#F57C00"),
			listOf(
				Subarea.Health(context),
				Subarea.Lounge(context),
				Subarea.Spirituality(context)
			)
		)
	
	class Personal(context: Context):
		Area(
			context,
			R.string.Area_name_personal,
			Color.parseColor("#EF233C"),
			listOf(
				Subarea.Emotional(context),
				Subarea.Happiness(context),
				Subarea.Intellect(context),
			)
		)
	
	class Professional(context: Context):
		Area(
			context,
			R.string.Area_name_professional,
			Color.parseColor("#4895EF"),
			listOf(
				Subarea.Career(context),
				Subarea.Contribution(context),
				Subarea.Finances(context)
			)
		)
	
	class Relationships(context: Context):
		Area(
			context,
			R.string.Area_name_relationships,
			Color.parseColor("#74C69D"),
			listOf(
				Subarea.Family(context),
				Subarea.Love(context),
				Subarea.Social(context)
			)
		)
	
	companion object {
		fun default(context: Context?) =
			context
				?.let {
					listOf(
						LifeQuality(context),
						Personal(context),
						Professional(context),
						Relationships(context)
					)
				}
				.orEmpty()
	}
}