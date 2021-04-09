package com.jeanbarrossilva.andre.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object MutableLiveDataX {
	fun <T> MutableLiveData<T>.immutable() = this as LiveData<T>
}