package com.jeanbarrossilva.andre.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

object TextViewX {
	fun EditText.doOnTextChanged(block: TextWatcher.(String) -> Unit) {
		val watcher =
			object: TextWatcher {
				override fun beforeTextChanged(
					s: CharSequence?,
					start: Int,
					count: Int,
					after: Int
				) = block(this, "$s")
				
				override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				}
				
				override fun afterTextChanged(s: Editable?) {
				}
			}
		addTextChangedListener(watcher)
	}
}