package com.jeanbarrossilva.andre.extension

object ListX {
	fun <E> List<E>.same(element: E): E? = find { it == element }
}