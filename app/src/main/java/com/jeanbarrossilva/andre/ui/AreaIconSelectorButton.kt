package com.jeanbarrossilva.andre.ui

import android.content.Context
import android.util.AttributeSet
import com.jeanbarrossilva.andre.extension.ContextX.primaryColor
import com.jeanbarrossilva.andre.extension.NumberX.dp
import top.defaults.drawabletoolbox.DrawableBuilder

class AreaIconSelectorButton: IconButton {
	constructor(context: Context): super(context)
	
	constructor(context: Context, attrs: AttributeSet?): super(context, attrs)
	
	constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):
		super(context, attrs, defStyleAttr)
	
	override fun setSelected(selected: Boolean) {
		background =
			if (selected)
				DrawableBuilder()
					.baseDrawable(background)
					.strokeWidth(2.dp(context))
					.strokeColor(context.primaryColor)
					.cornerRadius(20.dp(context))
					.build()
			else
				IconButton(context).background
	}
}