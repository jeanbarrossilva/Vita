package com.jeanbarrossilva.andre.database.subarea

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubareaDao {
	@Query("SELECT * FROM subareas WHERE id = :id")
	suspend fun findSubareaById(id: Int): SubareaEntity?
	
	@Query("SELECT * FROM subareas WHERE areaId = :areaId")
	suspend fun getSubareasOf(areaId: Int): List<SubareaEntity>
	
	@Query("SELECT * FROM subareas WHERE areaId = :areaId")
	fun getSubareasLiveDataOf(areaId: Int): LiveData<List<SubareaEntity>>
	
	@Insert
	suspend fun add(subarea: SubareaEntity)
	
	@Delete
	suspend fun remove(subarea: SubareaEntity)
}