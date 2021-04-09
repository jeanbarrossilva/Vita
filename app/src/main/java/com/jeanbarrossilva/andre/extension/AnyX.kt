package com.jeanbarrossilva.andre.extension

object AnyX {
	fun <T> T.getIf(condition: T.() -> Boolean, action: T.() -> T) =
		if (condition(this)) action(this) else this
}