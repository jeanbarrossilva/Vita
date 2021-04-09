package com.jeanbarrossilva.andre.extension

import androidx.annotation.StringRes
import com.google.android.material.button.MaterialButton

object MaterialButtonX {
	fun MaterialButton.setError(@StringRes res: Int) {
		error = context.getString(res)
	}
}