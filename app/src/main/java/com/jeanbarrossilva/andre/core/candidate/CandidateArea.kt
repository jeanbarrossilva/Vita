package com.jeanbarrossilva.andre.core.candidate

import androidx.annotation.ColorInt
import com.jeanbarrossilva.andre.database.area.AreaEntity

open class CandidateArea(
	open var title: String,
	@ColorInt open var color: Int,
	open val candidateSubareas: List<CandidateSubarea>
) {
	fun createEntity() = AreaEntity(title, color) to candidateSubareas
}