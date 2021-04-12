package com.jeanbarrossilva.andre.repo

import android.content.Context
import android.util.Log
import com.jeanbarrossilva.andre.core.Subarea
import com.jeanbarrossilva.andre.core.candidate.CandidateSubarea
import com.jeanbarrossilva.andre.database.AndreDatabase

object SubareaRepository {
	suspend fun findSubareaById(id: Int) =
		AndreDatabase.value.getSubareaDao().findSubareaById(id)
	
	suspend fun getSubareasOf(areaId: Int) =
		AndreDatabase.value.getSubareaDao().getSubareasOf(areaId).also {
			Log.d("SubareaRepository.getSubareasOf($areaId)", "$it")
		}
	
	fun getSubareasLiveDataOf(areaId: Int) =
		AndreDatabase.value.getSubareaDao().getSubareasLiveDataOf(areaId)
	
	suspend fun add(candidateSubarea: CandidateSubarea, context: Context, areaId: Int) {
		val entity = candidateSubarea.createEntity(context, areaId)
		AndreDatabase.value.getSubareaDao().add(entity)
		Log.d("SubareaRepository.add", "$entity")
	}
	
	suspend fun remove(subarea: Subarea) {
		val entity = findSubareaById(subarea.id)!!
		AndreDatabase.value.getSubareaDao().remove(entity)
		Log.d("SubareaRepository.remove", "$entity")
	}
}