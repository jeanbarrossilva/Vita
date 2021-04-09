package com.jeanbarrossilva.andre.core

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.jeanbarrossilva.andre.R
import java.io.Serializable

@Suppress("Unused")
sealed class SubareaIndicator(val title: String, @ColorInt val color: Int): Serializable {
	constructor(context: Context, @StringRes titleRes: Int, @ColorRes colorRes: Int):
		this(context.getString(titleRes), context.getColor(colorRes))
	
	data class Unset(private val context: Context):
		SubareaIndicator(
			context,
			R.string.SubareaIndicator_title_unset,
			R.color.SubareaIndicator_unset
		)

	data class Deficient(private val context: Context):
		SubareaIndicator(
			context,
			R.string.SubareaIndicator_title_deficient,
			R.color.SubareaIndicator_deficient
		)

	data class Unsatisfied(private val context: Context):
		SubareaIndicator(
			context,
			R.string.SubareaIndicator_title_unsatisfied,
			R.color.SubareaIndicator_unsatisfied
		)

	data class Acceptable(private val context: Context):
		SubareaIndicator(
			context,
			R.string.SubareaIndicator_title_acceptable,
			R.color.SubareaIndicator_acceptable
		)

	data class Satisfied(private val context: Context):
		SubareaIndicator(
			context,
			R.string.SubareaIndicator_title_satisfied,
			R.color.SubareaIndicator_satisfied
		)

	data class Realized(private val context: Context):
		SubareaIndicator(
			context,
			R.string.SubareaIndicator_title_realized,
			R.color.SubareaIndicator_realized
		)
	
	override fun toString() = "SubareaIndicator.${this::class.simpleName}"
	
	fun level(context: Context) =
		when (this) {
			is Unset -> throw IllegalAccessException("This SubareaIndicator is unset!")
			else -> values(context).indexOf(this) + 1
		}

	companion object {
		fun values(context: Context?) =
			context?.let {
				listOf(
					Deficient(context),
					Unsatisfied(context),
					Acceptable(context),
					Satisfied(context),
					Realized(context)
				)
			} ?: emptyList()
	}
}