package com.jeanbarrossilva.andre.extension

object MutableListX {
	fun <E> MutableList<E>.replace(oldElement: E, newElement: E) {
		val index = indexOf(oldElement)
		remove(oldElement)
		add(index, newElement)
	}
}