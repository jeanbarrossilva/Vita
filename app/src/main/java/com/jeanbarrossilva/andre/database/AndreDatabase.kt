package com.jeanbarrossilva.andre.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jeanbarrossilva.andre.database.area.AreaDao
import com.jeanbarrossilva.andre.database.area.AreaEntity
import com.jeanbarrossilva.andre.database.subarea.SubareaDao
import com.jeanbarrossilva.andre.database.subarea.SubareaEntity

@Database(entities = [AreaEntity::class, SubareaEntity::class], version = 1)
abstract class AndreDatabase: RoomDatabase() {
	abstract fun getAreaDao(): AreaDao
	
	abstract fun getSubareaDao(): SubareaDao
	
	companion object {
		lateinit var value: AndreDatabase
			private set
		
		fun init(context: Context) {
			if (!this::value.isInitialized)
				value = Room.databaseBuilder(context, AndreDatabase::class.java, "andre-db").build()
		}
	}
}