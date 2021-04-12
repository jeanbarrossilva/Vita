package com.jeanbarrossilva.andre.core

import androidx.annotation.ColorInt
import com.jeanbarrossilva.andre.repo.AreaRepository
import java.io.Serializable

@Suppress("Unused")
open class Area(
	open val id: Int,
	open val title: String,
	@ColorInt open val color: Int,
	open val subareas: List<Subarea>
): Serializable {
	suspend fun getEntity() = AreaRepository.findAreaById(id)
}