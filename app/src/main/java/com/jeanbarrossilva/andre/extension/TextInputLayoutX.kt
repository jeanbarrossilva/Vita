package com.jeanbarrossilva.andre.extension

import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout

object TextInputLayoutX {
	fun TextInputLayout.setError(@StringRes res: Int) {
		error = context.getString(res)
	}
}