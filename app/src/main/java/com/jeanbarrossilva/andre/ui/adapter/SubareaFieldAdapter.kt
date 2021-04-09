package com.jeanbarrossilva.andre.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.andre.core.Subarea
import com.jeanbarrossilva.andre.core.SubareaIndicator
import com.jeanbarrossilva.andre.databinding.ViewSubareaFieldBinding
import com.jeanbarrossilva.andre.extension.ContextX.layoutInflater
import com.jeanbarrossilva.andre.ui.viewholder.SubareaFieldViewHolder

class SubareaFieldAdapter(
	private val subareas: List<Subarea>,
	private val onSelectIndicator: (Subarea, SubareaIndicator) -> Unit,
	private val onDelete: (index: Int) -> Boolean
): RecyclerView.Adapter<SubareaFieldViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		SubareaFieldViewHolder(
			ViewSubareaFieldBinding.inflate(parent.context.layoutInflater, parent, false)
		)
	
	override fun onBindViewHolder(holder: SubareaFieldViewHolder, position: Int) =
		holder.bind(subareas[position], onSelectIndicator, onDelete = { index ->
			val didDelete = onDelete(index)
			if (didDelete)
				notifyItemRemoved(index)
		})
	
	override fun getItemCount() = subareas.size
}