package com.jeanbarrossilva.andre.core

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

data class BottomSheetOption(
	val icon: Drawable,
	val title: String,
	val dismissesDialog: Boolean = true,
	val action: () -> Unit,
) {
	constructor(
		context: Context?,
		@DrawableRes iconRes: Int,
		@StringRes titleRes: Int,
		dismissesDialog: Boolean = true,
		action: () -> Unit,
	): this(
		ContextCompat.getDrawable(context!!, iconRes)!!,
		context.getString(titleRes),
		dismissesDialog,
		action
	)
}