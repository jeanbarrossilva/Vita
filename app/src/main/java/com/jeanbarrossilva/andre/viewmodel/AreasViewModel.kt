package com.jeanbarrossilva.andre.viewmodel

import android.content.Intent
import android.content.res.ColorStateList
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeanbarrossilva.andre.BuildConfig
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.activity.AreaComposerActivity
import com.jeanbarrossilva.andre.core.Area
import com.jeanbarrossilva.andre.core.SubareaIndicator
import com.jeanbarrossilva.andre.extension.ActivityX.withFab
import com.jeanbarrossilva.andre.extension.NavControllerX.navigateOnce
import com.jeanbarrossilva.andre.fragment.AreasFragment
import com.jeanbarrossilva.andre.fragment.AreasFragmentDirections
import com.jeanbarrossilva.andre.repo.AreaRepository
import com.jeanbarrossilva.andre.ui.adapter.AreaAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AreasViewModel(private val fragment: AreasFragment): ViewModel() {
	private val navController = fragment.findNavController()
	
	private fun withAreas(block: (List<Area>) -> Unit) =
		AreaRepository.getAreasLiveData().observe(fragment) { entities ->
			viewModelScope.launch(Dispatchers.IO) {
				val areas = entities.map { entity -> entity.createArea(fragment.requireContext()) }
				fragment.activity?.runOnUiThread { block(areas) }
			}
		}
	
	private fun configSubareaIndicatorsForDebugging() {
		if (BuildConfig.DEBUG)
			withAreas { areas ->
				areas.forEach { area ->
					area.subareas.forEach { subarea ->
						subarea.indicator = SubareaIndicator.values(fragment.context).random()
					}
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
		withAreas { areas ->
			fragment.binding.emptyListView.isVisible = areas.isEmpty()
			fragment.binding.areasView.isVisible = areas.isNotEmpty()
			fragment.binding.root.invalidate()
		}
	
	fun showAreas() {
		withAreas { areas ->
			fragment.binding.areasView.adapter =
				AreaAdapter(
					areas,
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