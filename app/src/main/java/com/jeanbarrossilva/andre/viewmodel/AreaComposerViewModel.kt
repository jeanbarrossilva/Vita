package com.jeanbarrossilva.andre.viewmodel

import android.content.res.ColorStateList
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.core.Area
import com.jeanbarrossilva.andre.core.Subarea
import com.jeanbarrossilva.andre.core.SubareaIndicator
import com.jeanbarrossilva.andre.extension.ActivityX.closeKeyboard
import com.jeanbarrossilva.andre.extension.ActivityX.withFab
import com.jeanbarrossilva.andre.extension.ListX.same
import com.jeanbarrossilva.andre.extension.MaterialButtonX.setError
import com.jeanbarrossilva.andre.extension.TextInputLayoutX.setError
import com.jeanbarrossilva.andre.fragment.AreaComposerFragment
import com.jeanbarrossilva.andre.repo.AreaRepository
import com.jeanbarrossilva.andre.ui.adapter.SubareaFieldAdapter
import com.jeanbarrossilva.andre.ui.viewholder.SubareaFieldViewHolder

class AreaComposerViewModel(private val fragment: AreaComposerFragment): ViewModel() {
	private val Subarea.fieldViewHolder: SubareaFieldViewHolder?
		get() {
			val recyclerView = fragment.binding.subareaFieldsView
			val viewHolder = recyclerView.findViewHolderForAdapterPosition(subareas.indexOf(this))
			return viewHolder as? SubareaFieldViewHolder
		}
	
	private var title = ""
	private var color = SubareaIndicator.Unset(fragment.requireContext()).color
	private val defaultSubarea = Subarea.empty(fragment.context)
	private val subareas = mutableListOf(defaultSubarea)
	
	private fun cleanErrors() =
		with(fragment.binding) {
			titleFieldLayout.error = null
			addSubareaFieldButton.error = null
			subareas.forEach { subarea ->
				subarea.fieldViewHolder?.binding?.titleFieldLayout?.error = null
			}
		}
	
	private fun extractValues() {
		title = fragment.binding.titleFieldLayout.editText!!.text.toString()
		subareas.forEach { subarea ->
			subarea.title = "${subarea.fieldViewHolder?.binding?.titleFieldLayout?.editText?.text}"
			subarea.indicator =
				SubareaIndicator.values(fragment.context).find { indicator ->
					indicator.title == "${subarea.fieldViewHolder?.binding?.indicatorButton?.text}"
				}!!
		}
	}
	
	private fun checkForErrors(onSuccess: () -> Unit) {
		var errorCount = 0
		val didSucceed = { errorCount == 0 }
		
		with(fragment.binding) {
			if (title.isEmpty()) {
				titleFieldLayout.setError(R.string.AreaComposerFragment_error_empty_title)
				errorCount++
			}
			
			if (subareas.isEmpty()) {
				addSubareaFieldButton.setError(R.string.AreaComposerFragment_error_empty_subareas)
				errorCount++
			}
			
			if (subareas.any { subarea -> subarea.title.isBlank() })
				subareas.forEach { subarea ->
					if (subarea.title.isBlank()) {
						subarea.fieldViewHolder?.binding?.titleFieldLayout?.setError(R.string.AreaComposerFragment_error_empty_title)
						errorCount++
					}
				}
		}
		
		if (didSucceed())
			onSuccess()
	}
	
	private fun addArea() {
		cleanErrors()
		extractValues()
		checkForErrors {
			AreaRepository.add(Area(title, color, subareas))
			fragment.activity?.finish()
		}
	}
	
	fun configFab() {
		fragment.activity?.withFab {
			backgroundTintList =
				ColorStateList.valueOf(context.getColor(android.R.color.holo_green_light))
			setImageResource(R.drawable.ic_done)
			setOnClickListener { addArea() }
		}
	}
	
	fun configSubareaFields() {
		val onSelectIndicator = { subarea: Subarea, indicator: SubareaIndicator ->
			subareas.same(subarea)?.indicator = indicator
		}
		val onDelete: (Int) -> Boolean = { index ->
			val canDelete = subareas[index] != defaultSubarea
			canDelete.also { if (canDelete) subareas.removeAt(index) }
		}
		
		fragment.binding.subareaFieldsView.adapter =
			SubareaFieldAdapter(subareas, onSelectIndicator, onDelete)
		fragment.binding.subareaFieldsView.layoutManager = LinearLayoutManager(fragment.context)
	}
	
	fun configAddSubareaButton() =
		fragment.binding.addSubareaFieldButton.setOnClickListener {
			subareas.add(Subarea.empty(fragment.context))
			fragment.binding.subareaFieldsView.adapter?.notifyItemInserted(subareas.lastIndex)
		}
	
	fun closeKeyboard() {
		fragment.activity?.closeKeyboard()
	}
}