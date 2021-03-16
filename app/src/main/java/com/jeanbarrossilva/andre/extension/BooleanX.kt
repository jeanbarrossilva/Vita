package com.jeanbarrossilva.andre.extension

object BooleanX {
	fun Boolean?.orFalse() = this ?: false
}