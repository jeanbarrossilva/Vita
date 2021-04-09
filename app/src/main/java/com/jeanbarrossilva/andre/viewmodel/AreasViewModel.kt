package com.jeanbarrossilva.andre.viewmodel

import android.content.Intent
import android.content.res.ColorStateList
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeanbarrossilva.andre.BuildConfig
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.activity.AreaComposerActivity
import com.jeanbarrossilva.andre.core.SubareaIndicator
import com.jeanbarrossilva.andre.extension.ActivityX.withFab
import com.jeanbarrossilva.andre.extension.NavControllerX.navigateOnce
import com.jeanbarrossilva.andre.fragment.AreasFragment
import com.jeanbarrossilva.andre.fragment.AreasFragmentDirections
import com.jeanbarrossilva.andre.repo.AreaRepository
import com.jeanbarrossilva.andre.ui.adapter.AreaAdapter

class AreasViewModel(private val fragment: AreasFragment): ViewModel() {
	private val navController = fragment.findNavController()
	private val areas = AreaRepository.getAreas()
	
	private fun configSubareaIndicatorsForDebugging() {
		if (BuildConfig.DEBUG)
			areas.value?.forEach { area ->
				area.subareas.forEach { subarea ->
					subarea.indicator = SubareaIndicator.values(fragment.context).random()
				}
			}
	}
	
	fun configFab() {
		fragment.activity?.withFab {
			backgroundTintList =
				ColorStateList.valueOf(context.getColor(R.color.primary))
			setImageResource(R.drawable.ic_add)
			setOnClickListener {
				fragment.startActivity(Intent(context, AreaComposerActivity::class.java))
			}
		}
	}
	
	fun configEmptyListViewVisibility() =
		areas.observe(fragment) {
			fragment.binding.emptyListView.isVisible = it.isEmpty()
			fragment.binding.areasView.isVisible = it.isNotEmpty()
			fragment.binding.root.invalidate()
		}
	
	fun showAreas() {
		areas.observe(fragment) {
			fragment.binding.areasView.adapter =
				AreaAdapter(
					areas = it,
					onLongClick = { area ->
						val directions = AreasFragmentDirections.showOptionsOf(area)
						navController.navigateOnce(R.id.areaOptionsBottomSheetFragment, directions)
					}
				)
		}
		fragment.binding.areasView.layoutManager = LinearLayoutManager(fragment.context)
	}
	
	init {
		configSubareaIndicatorsForDebugging()
	}
}