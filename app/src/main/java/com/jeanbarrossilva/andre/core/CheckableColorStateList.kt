package com.jeanbarrossilva.andre.core

import android.content.res.ColorStateList
import androidx.annotation.ColorInt

class CheckableColorStateList(@ColorInt uncheckedColor: Int, @ColorInt checkedColor: Int):
	ColorStateList(
		arrayOf(
			intArrayOf(-android.R.attr.state_checked),
			intArrayOf(android.R.attr.state_checked)
		),
		intArrayOf(uncheckedColor, checkedColor)
	)