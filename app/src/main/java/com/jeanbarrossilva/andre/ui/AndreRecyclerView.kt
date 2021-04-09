package com.jeanbarrossilva.andre.ui

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.extension.AnyX.getIf
import com.jeanbarrossilva.andre.extension.ContextX.withStyledAttributes
import com.jeanbarrossilva.andre.ui.itemdecoration.AndreItemDecoration
import top.defaults.drawabletoolbox.DrawableBuilder
import kotlin.math.roundToInt

class AndreRecyclerView: RecyclerView {
	var spacing = 0
		set(value) {
			field = value
			setSpacingOf(value)
		}
	
	constructor(context: Context): super(context) {
		getAttributes()
	}
	
	constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
		getAttributes(attrs)
	}
	
	constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):
		super(context, attrs, defStyleAttr) {
			getAttributes(attrs, defStyleAttr)
		}
	
	private fun setSpacingOf(spacing: Int) {
		val layoutManager =
			this.layoutManager as? LinearLayoutManager ?: LinearLayoutManager(context)
		val orientation = layoutManager.orientation
		val decoration =
			AndreItemDecoration(context, orientation).apply {
				setDrawable(
					DrawableBuilder()
						.getIf({ orientation == HORIZONTAL }) { width(spacing) }
						.getIf({ orientation == VERTICAL }) { height(spacing) }
						.build()
				)
			}
		
		this.layoutManager = layoutManager
		addItemDecoration(decoration)
	}
	
	private fun getAttributes(attrs: AttributeSet? = null, defStyleAttr: Int = 0) =
		context.withStyledAttributes(attrs, defStyleAttr, R.styleable.AndreRecyclerView) {
			if (it == R.styleable.AndreRecyclerView_android_spacing)
				spacing = getDimension(it, spacing.toFloat()).roundToInt()
		}
}