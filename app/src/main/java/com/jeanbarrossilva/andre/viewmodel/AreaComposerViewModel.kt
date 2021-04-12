package com.jeanbarrossilva.andre.viewmodel

import android.content.res.ColorStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.core.SubareaIndicator
import com.jeanbarrossilva.andre.core.candidate.CandidateArea
import com.jeanbarrossilva.andre.core.candidate.CandidateSubarea
import com.jeanbarrossilva.andre.extension.ActivityX.closeKeyboard
import com.jeanbarrossilva.andre.extension.ActivityX.withFab
import com.jeanbarrossilva.andre.extension.ListX.same
import com.jeanbarrossilva.andre.extension.MaterialButtonX.setError
import com.jeanbarrossilva.andre.extension.TextInputLayoutX.setError
import com.jeanbarrossilva.andre.fragment.AreaComposerFragment
import com.jeanbarrossilva.andre.repo.AreaRepository
import com.jeanbarrossilva.andre.ui.adapter.SubareaFieldAdapter
import com.jeanbarrossilva.andre.ui.viewholder.SubareaFieldViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AreaComposerViewModel(private val fragment: AreaComposerFragment): ViewModel() {
	private val CandidateSubarea.fieldViewHolder: SubareaFieldViewHolder?
		get() {
			val recyclerView = fragment.binding.subareaFieldsView
			val viewHolder = recyclerView.findViewHolderForAdapterPosition(incompleteSubareas.indexOf(this))
			return viewHolder as? SubareaFieldViewHolder
		}
	
	private var title = ""
	private var color = SubareaIndicator.Unset(fragment.requireContext()).color
	private val incompleteSubareas = mutableListOf(createIncompleteSubarea())
	
	private fun createIncompleteSubarea() = CandidateSubarea(fragment.requireContext())
	
	private fun cleanErrors() =
		with(fragment.binding) {
			titleFieldLayout.error = null
			addSubareaFieldButton.error = null
			incompleteSubareas.forEach { subarea ->
				subarea.fieldViewHolder?.binding?.titleFieldLayout?.error = null
			}
		}
	
	private fun extractValues() {
		title = fragment.binding.titleFieldLayout.editText!!.text.toString()
		incompleteSubareas.forEach { incompleteSubarea ->
			incompleteSubarea.title =
				"${incompleteSubarea.fieldViewHolder?.binding?.titleFieldLayout?.editText?.text}"
			incompleteSubarea.indicator =
				SubareaIndicator.values(fragment.context).find { indicator ->
					indicator.title == "${incompleteSubarea.fieldViewHolder?.binding?.indicatorButton?.text}"
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
			
			if (incompleteSubareas.isEmpty()) {
				addSubareaFieldButton.setError(R.string.AreaComposerFragment_error_empty_subareas)
				errorCount++
			}
			
			if (incompleteSubareas.any { subarea -> subarea.title.isBlank() })
				incompleteSubareas.forEach { subarea ->
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
			viewModelScope.launch(Dispatchers.IO) {
				val candidateArea = CandidateArea(title, color, incompleteSubareas)
				AreaRepository.add(candidateArea, fragment.requireContext())
			}
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
		val onSelectIndicator = {
				candidateSubarea: CandidateSubarea, indicator: SubareaIndicator ->
			incompleteSubareas.same(candidateSubarea)?.indicator = indicator
		}
		val onDelete: (Int) -> Boolean = { index ->
			val canDelete = incompleteSubareas.size > 1
			canDelete.also { if (canDelete) incompleteSubareas.removeAt(index) }
		}
		
		fragment.binding.subareaFieldsView.adapter =
			SubareaFieldAdapter(incompleteSubareas, onSelectIndicator, onDelete)
		fragment.binding.subareaFieldsView.layoutManager = LinearLayoutManager(fragment.context)
	}
	
	fun configAddSubareaButton() =
		fragment.binding.addSubareaFieldButton.setOnClickListener {
			incompleteSubareas.add(createIncompleteSubarea())
			fragment.binding.subareaFieldsView.adapter?.notifyItemInserted(incompleteSubareas.lastIndex)
		}
	
	fun closeKeyboard() {
		fragment.activity?.closeKeyboard()
	}
}