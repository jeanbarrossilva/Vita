package com.jeanbarrossilva.andre

import android.app.Application
import com.jeanbarrossilva.andre.database.AndreDatabase

class AndreApplication: Application() {
	override fun onCreate() {
		super.onCreate()
		AndreDatabase.init(this)
	}
}