package com.jeanbarrossilva.andre.core

import androidx.annotation.StringRes
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.extension.ListX.greater

@Suppress("Unused")
sealed class SubareaIndicator(@StringRes val titleRes: Int) {
	object Unset: SubareaIndicator(R.string.SubareaIndicator_title_unset)

	object Deficient: SubareaIndicator(R.string.SubareaIndicator_title_deficient)

	object Unsatisfied: SubareaIndicator(R.string.SubareaIndicator_title_unsatisfied)

	object Acceptable: SubareaIndicator(R.string.SubareaIndicator_title_acceptable)

	object Satisfied: SubareaIndicator(R.string.SubareaIndicator_title_satisfied)

	object Realized: SubareaIndicator(R.string.SubareaIndicator_title_realized)

	fun level() =
		when (this) {
			is Unset -> throw IllegalAccessException("This SubareaIndicator is unset!")
			else -> values.indexOf(this) + 1
		}

	fun levelAsPercentage() = values.map { indicator -> indicator.level() }.greater() / level()

	companion object {
		val values = listOf(Deficient, Unsatisfied, Acceptable, Satisfied, Realized)
	}
}