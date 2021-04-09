package com.jeanbarrossilva.andre

import android.app.Application
import com.jeanbarrossilva.andre.repo.AreaRepository

class AndreApplication: Application() {
	override fun onCreate() {
		super.onCreate()
		AreaRepository.init(this)
	}
}