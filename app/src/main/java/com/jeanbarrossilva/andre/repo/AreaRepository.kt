package com.jeanbarrossilva.andre.repo

import android.content.Context
import android.util.Log
import com.jeanbarrossilva.andre.core.Area
import com.jeanbarrossilva.andre.core.candidate.CandidateArea
import com.jeanbarrossilva.andre.database.AndreDatabase

object AreaRepository {
	suspend fun getAreas() = AndreDatabase.value.getAreaDao().getAreas()
	
	fun getAreasLiveData() = AndreDatabase.value.getAreaDao().getAreasLiveData()
	
	suspend fun findAreaById(id: Int) = AndreDatabase.value.getAreaDao().findAreaById(id)
	
	suspend fun add(candidateArea: CandidateArea, context: Context) {
		val (entity, _) = candidateArea.createEntity()
		val entityId = AndreDatabase.value.getAreaDao().add(entity)
		
		Log.d("AreaRepository.add", "$entity")
		candidateArea.candidateSubareas.forEach {
			SubareaRepository.add(it, context, areaId = entityId.toInt())
		}
	}
	
	suspend fun remove(area: Area) {
		val entity = findAreaById(area.id)!!
		AndreDatabase.value.getAreaDao().remove(entity)
		Log.d("AreaRepository.remove", "$entity")
		area.subareas.forEach {
			SubareaRepository.remove(it)
		}
	}
}