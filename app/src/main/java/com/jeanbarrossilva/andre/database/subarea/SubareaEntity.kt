package com.jeanbarrossilva.andre.database.subarea

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbarrossilva.andre.core.Subarea
import com.jeanbarrossilva.andre.core.SubareaIndicator

@Entity(tableName = "subareas")
data class SubareaEntity(
	@PrimaryKey(autoGenerate = true) val id: Int,
	val areaId: Int,
	val title: String,
	val indicator: Int
) {
	constructor(areaId: Int, title: String, indicator: Int): this(0, areaId, title, indicator)
	
	override fun toString() = "Subarea(id=$id, areaId=$areaId, title=$title)"
	
	fun createSubarea(context: Context) =
		Subarea(context, id, title).apply {
			val indicators = SubareaIndicator.values(context)
			this.indicator =
				indicators.find { it.level(context) == this@SubareaEntity.indicator }!!
		}
}