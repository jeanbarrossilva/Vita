package com.jeanbarrossilva.andre.extension

import androidx.navigation.NavController
import androidx.navigation.NavDirections

object NavControllerX {
	fun NavController.navigateOnce(destinationId: Int, directions: NavDirections) {
		if (currentDestination?.id != destinationId)
			navigate(directions)
	}
}