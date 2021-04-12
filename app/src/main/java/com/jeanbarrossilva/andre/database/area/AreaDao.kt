package com.jeanbarrossilva.andre.database.area

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AreaDao {
	@Query("SELECT * FROM areas")
	suspend fun getAreas(): List<AreaEntity>
	
	@Query("SELECT * FROM areas")
	fun getAreasLiveData(): LiveData<List<AreaEntity>>
	
	@Query("SELECT * FROM areas WHERE id = :id")
	suspend fun findAreaById(id: Int): AreaEntity?
	
	@Insert
	suspend fun add(area: AreaEntity): Long
	
	@Delete
	suspend fun remove(area: AreaEntity)
}