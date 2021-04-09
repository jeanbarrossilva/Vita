package com.jeanbarrossilva.andre.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.andre.core.Subarea
import com.jeanbarrossilva.andre.core.SubareaIndicator
import com.jeanbarrossilva.andre.databinding.ViewSubareaFieldBinding
import com.jeanbarrossilva.andre.extension.MenuX.add

class SubareaFieldViewHolder(val binding: ViewSubareaFieldBinding):
	RecyclerView.ViewHolder(binding.root) {
	fun bind(
		subarea: Subarea,
		onSelectIndicator: (Subarea, SubareaIndicator) -> Unit,
		onDelete: (index: Int) -> Unit
	) {
		val context = binding.root.context
		val indicators = SubareaIndicator.values(context)
		
		binding.titleFieldLayout.editText?.setText(subarea.title)
		with(binding.indicatorButton) {
			indicators.forEachIndexed { index, indicator ->
				if (index == 0)
					text = indicator.title
				menu.add(indicator.title) {
					text = title
					onSelectIndicator(subarea, indicator)
				}
			}
		}
		binding.deleteButton.setOnClickListener { onDelete(layoutPosition) }
	}
}