package com.jeanbarrossilva.andre.ui.itemdecoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class AndreItemDecoration(context: Context, orientation: Int):
	DividerItemDecoration(context, orientation) {
	override fun getItemOffsets(
		outRect: Rect,
		view: View,
		parent: RecyclerView,
		state: RecyclerView.State
	) {
		val isLastItem = parent.getChildAdapterPosition(view) == state.itemCount
		if (isLastItem) outRect.setEmpty() else super.getItemOffsets(outRect, view, parent, state)
	}
}