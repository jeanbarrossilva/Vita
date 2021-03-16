package com.jeanbarrossilva.andre.interop

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange

data class AndreChartEntry(
	val title: String,
	val icon: Drawable,
	@FloatRange(from = 0.0, to = 1.0) val value: Float = 0f,
	@ColorInt val color: Int
)