package com.jeanbarrossilva.andre.extension

import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog

object MaterialDialogX {
	fun MaterialDialog.title(@StringRes res: Int, vararg format: String) =
		title(text = context.getString(res).format(*format))
}