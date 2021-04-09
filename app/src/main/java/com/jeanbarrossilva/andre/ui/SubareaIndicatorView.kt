package com.jeanbarrossilva.andre.ui

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import androidx.core.view.children
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.core.Subarea
import com.jeanbarrossilva.andre.core.SubareaIndicator
import com.jeanbarrossilva.andre.extension.NumberX.dp

class SubareaIndicatorView: AndreLinearLayout {
	private val bubbles = children.filterIsInstance<CardView>()
	private val unsetBubbleColor = context.getColor(R.color.SubareaIndicator_unset)
	
	var subarea: Subarea? = null
		set(value) {
			field = value
			updateBubbles()
		}
	
	constructor(context: Context): super(context)
	
	constructor(context: Context, attrs: AttributeSet?): super(context, attrs)
	
	constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):
		super(context, attrs, defStyleAttr)
	
	private fun createBubbleView() =
		CardView(context).apply {
			cardElevation = 0f
			radius = 50.dp(context).toFloat()
			layoutParams = LayoutParams(15.dp(context), 15.dp(context))
			setCardBackgroundColor(unsetBubbleColor)
		}
	
	private fun addBubbles() =
		SubareaIndicator.values(context).forEach { _ -> addView(createBubbleView()) }
	
	private fun updateBubbles() {
		subarea?.let { subarea ->
			val range = 0 until subarea.indicator.level(context)
			range.forEach { index ->
				bubbles.elementAt(index).setCardBackgroundColor(subarea.indicator.color)
			}
		} ?: run {
			bubbles.forEach { bubble -> bubble.setCardBackgroundColor(unsetBubbleColor) }
		}
	}
	
	init {
		horizontalSpacing = 10.dp(context).toFloat()
		addBubbles()
	}
}