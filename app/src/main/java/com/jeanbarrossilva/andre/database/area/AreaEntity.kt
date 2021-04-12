package com.jeanbarrossilva.andre.database.area

import android.content.Context
import androidx.annotation.ColorInt
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbarrossilva.andre.core.Area
import com.jeanbarrossilva.andre.repo.SubareaRepository

@Entity(tableName = "areas")
data class AreaEntity(
	@PrimaryKey(autoGenerate = true) val id: Int,
	val title: String,
	@ColorInt val color: Int
) {
	constructor(title: String, @ColorInt color: Int): this(0, title, color)
	
	override fun toString() = "Area(id=$id, title=$title, color=$color)"
	
	suspend fun createArea(context: Context): Area {
		val subareaEntities = SubareaRepository.getSubareasOf(id)
		val subareas = subareaEntities.map { entity -> entity.createSubarea(context) }
		return Area(id, title, color, subareas)
	}
}