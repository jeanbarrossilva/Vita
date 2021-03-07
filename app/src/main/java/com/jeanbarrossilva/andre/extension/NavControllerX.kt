package com.jeanbarrossilva.andre.extension

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.navOptions

object NavControllerX {
	private fun NavController.canNavigateFrom(initialDestinationId: Int) =
		currentDestination?.id == initialDestinationId
	
	fun NavController.navigateAnimating(
		destinationRes: Int,
		enterAnimationRes: Int = androidx.fragment.R.animator.fragment_open_enter,
		exitAnimationRes: Int = androidx.fragment.R.animator.fragment_close_exit,
		popEnterAnimationRes: Int = enterAnimationRes,
		popExitAnimationRes: Int = exitAnimationRes
	) = navigate(destinationRes, null, navOptions {
		anim {
			enter = enterAnimationRes
			exit = exitAnimationRes
			popEnter = popEnterAnimationRes
			popExit = popExitAnimationRes
		}
	})
	
	@JvmName("navigateOnceToDirections")
	fun NavController.navigateOnceFrom(navigation: Pair<Int, NavDirections>) =
		navigation.let { (initialDestinationId, directions) ->
			if (canNavigateFrom(initialDestinationId))
				navigate(directions)
		}
	
	@JvmName("navigateOnceWithAction")
	fun NavController.navigateOnceFrom(navigation: Pair<Int, Int>) =
		navigation.let { (initialDestinationId, actionRes) ->
			if (canNavigateFrom(initialDestinationId))
				navigate(actionRes)
		}
}