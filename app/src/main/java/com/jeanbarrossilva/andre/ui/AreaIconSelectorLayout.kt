package com.jeanbarrossilva.andre.ui

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.core.view.setMargins
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.JustifyContent
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.extension.ContextX.colorOf
import com.jeanbarrossilva.andre.extension.NumberX.dp

class AreaIconSelectorLayout: AndreFlexboxLayout {
	private val iconResources =
		listOf(R.drawable.ic_book, R.drawable.ic_favorite, R.drawable.ic_help, R.drawable.ic_work)
	private val onSelectListeners = mutableListOf<OnSelectListener>()
	
	private lateinit var iconButton: (Int) -> IconButton
	
	interface OnSelectListener {
		fun onSelect(@DrawableRes iconRes: Int)
		
		companion object {
			operator fun invoke(block: (iconRes: Int) -> Unit) =
				object : OnSelectListener {
					override fun onSelect(@DrawableRes iconRes: Int) = block(iconRes)
				}
		}
	}
	
	constructor(context: Context): super(context) {
		init()
	}
	
	constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
		init(attrs)
	}
	
	constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):
		super(context, attrs, defStyleAttr) {
			init(attrs, defStyleAttr)
		}
	
	private fun triggerOnSelectListeners(@DrawableRes iconRes: Int) =
		onSelectListeners.forEach { it.onSelect(iconRes) }
	
	private fun initViews(attrs: AttributeSet?, defStyleAttr: Int) {
		iconButton = { iconRes ->
			IconButton(context, attrs, defStyleAttr).apply {
				layoutParams =
					MarginLayoutParams(80.dp(context), 80.dp(context)).apply {
						setMargins(4.dp(context))
					}
				setImageResource(iconRes, tint = context colorOf android.R.attr.textColorPrimary)
				setOnClickListener { triggerOnSelectListeners(iconRes) }
			}
		}
	}
	
	private fun addViews() = iconResources.forEach { addView(iconButton(it)) }
	
	private fun init(attrs: AttributeSet? = null, defStyleAttr: Int = 0) {
		initViews(attrs, defStyleAttr)
		addViews()
	}
	
	private fun setOnSelectIconListener(listener: OnSelectListener) =
		onSelectListeners.add(listener)
	
	fun setOnSelectIconListener(listener: (Int) -> Unit) =
		setOnSelectIconListener(OnSelectListener { listener(it) })
	
	init {
		justifyContent = JustifyContent.SPACE_BETWEEN
		flexWrap = FlexWrap.WRAP
		setOnClickListener { isSelected = !isSelected }
		setSpacing(10.dp(context).toFloat())
	}
}