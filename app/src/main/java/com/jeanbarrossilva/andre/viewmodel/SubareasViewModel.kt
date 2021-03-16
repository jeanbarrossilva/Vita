package com.jeanbarrossilva.andre.viewmodel

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.jeanbarrossilva.andre.BuildConfig
import com.jeanbarrossilva.andre.core.Subarea
import com.jeanbarrossilva.andre.core.SubareaIndicator
import com.jeanbarrossilva.andre.extension.ChartX.setExtraOffsets
import com.jeanbarrossilva.andre.fragment.SubareasFragment
import com.jeanbarrossilva.andre.fragment.SubareasFragmentDirections
import com.jeanbarrossilva.andre.interop.AndreChartEntry
import com.jeanbarrossilva.andre.interop.implementation.AndrePieChart

class SubareasViewModel(private val fragment: SubareasFragment): ViewModel() {
	private val area = fragment.area
	private val subareas = area.subareas
	
	private fun setIndicatorsRandomlyForSubareas() {
		subareas.forEach { subarea ->
			if (subarea.indicator is SubareaIndicator.Unset) {
				subarea.indicator = SubareaIndicator.values.random()
				Log.d(
					"SubareasViewModel.setIndicatorsRandomlyForSubareas",
					"${subarea::class.simpleName}'s indicator = " +
						"${subarea.indicator::class.simpleName}"
				)
			}
		}
	}
	
	private fun navigateToDetailsOf(subarea: Subarea) =
		fragment.findNavController().navigate(SubareasFragmentDirections.toDetailsOf(subarea))
	
	fun configSubareasForDebugging() {
		if (BuildConfig.DEBUG)
			setIndicatorsRandomlyForSubareas()
	}
	
	fun showSubareasInChart() {
		val entries =
			subareas.map { subarea ->
				AndreChartEntry(
					subarea.title,
					ContextCompat.getDrawable(fragment.requireContext(), subarea.iconRes)!!,
					subarea.indicator.levelAsPercentage(),
					subarea.color
				)
			}
		val chart =
			AndrePieChart(fragment.requireContext()).apply {
				showsEntryValues = false
				onSelectEntry = {
					val subarea = subareas[entries.indexOf(it)]
					navigateToDetailsOf(subarea)
				}
				add(entries)
				view.setExtraOffsets(30f)
			}
		
		fragment.binding.chartLayout.addView(chart.view)
	}
}