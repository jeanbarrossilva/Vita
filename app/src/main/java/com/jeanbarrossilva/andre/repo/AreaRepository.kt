package com.jeanbarrossilva.andre.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.jeanbarrossilva.andre.core.Area
import com.jeanbarrossilva.andre.extension.MutableLiveDataX.immutable

object AreaRepository {
	private val areas = MutableLiveData(emptyList<Area>())
	
	fun init(context: Context) {
		areas.value = Area.default(context) + areas.value!!
	}
	
	fun getAreas() = areas.immutable()
	
	fun add(area: Area) {
		areas.value = areas.value!! + area
	}
	
	fun remove(area: Area) {
		areas.value = areas.value!! - area
	}
}