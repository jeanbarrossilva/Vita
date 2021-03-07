package com.jeanbarrossilva.andre.extension

import androidx.annotation.IntRange
import com.google.android.material.textfield.TextInputLayout
import com.jeanbarrossilva.andre.extension.EditTextX.doSilentlyOnTextChanged
import com.jeanbarrossilva.andre.extension.IntX.numberOfDigits

object TextInputLayoutX {
	val TextInputLayout.field get() = editText!!
	
	fun TextInputLayout.asPercentageField(
		@IntRange(from = 0, to = 100) minimum: Int = 0,
		@IntRange(from = 0, to = 100) maximum: Int = 100
	) {
		suffixText = "%"
		field.doSilentlyOnTextChanged { text ->
			val value = text.toInt()
			when {
				value > maximum || text.length > maximum.numberOfDigits -> setText("$maximum")
				value < minimum -> setText("$minimum")
			}
		}
		field.setText("$minimum")
	}
}