package com.jeanbarrossilva.andre.core

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.core.candidate.CandidateArea
import com.jeanbarrossilva.andre.core.candidate.CandidateSubarea

sealed class DefaultArea(
	override var title: String,
	override var color: Int,
	override val candidateSubareas: List<CandidateSubarea>
): CandidateArea(title, color, candidateSubareas) {
	constructor(
		context: Context,
		@StringRes titleRes: Int,
		@ColorInt color: Int,
		subareas: List<CandidateSubarea>
	): this(context.getString(titleRes), color, subareas)
	
	data class LifeQuality(private val context: Context):
		DefaultArea(
			context,
			R.string.Area_name_life_quality,
			Color.parseColor("#F57C00"),
			listOf(
				CandidateSubarea(context, R.string.Subarea_name_health),
				CandidateSubarea(context, R.string.Subarea_name_lounge),
				CandidateSubarea(context, R.string.Subarea_name_spirituality)
			)
		)
	
	data class Personal(private val context: Context):
		DefaultArea(
			context,
			R.string.Area_name_personal,
			Color.parseColor("#EF233C"),
			listOf(
				CandidateSubarea(context, R.string.Subarea_name_emotional),
				CandidateSubarea(context, R.string.Subarea_name_happiness),
				CandidateSubarea(context, R.string.Subarea_name_intellect),
			)
		)
	
	class Professional(context: Context):
		DefaultArea(
			context,
			R.string.Area_name_professional,
			Color.parseColor("#4895EF"),
			listOf(
				CandidateSubarea(context, R.string.Subarea_name_career),
				CandidateSubarea(context, R.string.Subarea_name_contribution),
				CandidateSubarea(context, R.string.Subarea_name_finances)
			)
		)
	
	class Relationships(context: Context):
		DefaultArea(
			context,
			R.string.Area_name_relationships,
			Color.parseColor("#74C69D"),
			listOf(
				CandidateSubarea(context, R.string.Subarea_name_family),
				CandidateSubarea(context, R.string.Subarea_name_love),
				CandidateSubarea(context, R.string.Subarea_name_social)
			)
		)
}