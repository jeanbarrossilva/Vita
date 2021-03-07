package com.jeanbarrossilva.andre.ui

import android.content.Context
import android.util.AttributeSet
import com.google.android.flexbox.FlexboxLayout
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.extension.ContextX.withStyledAttributes
import top.defaults.drawabletoolbox.DrawableBuilder

@Suppress("MemberVisibilityCanBePrivate")
open class AndreFlexboxLayout: FlexboxLayout {
	var horizontalSpacing = 0f
		set(value) {
			field = value
			setHorizontalSpacingOf(value)
		}
	var verticalSpacing = 0f
		set(value) {
			field = value
			setVerticalSpacingOf(value)
		}
	
	private val drawableBuilder =
		DrawableBuilder().width(horizontalSpacing.toInt()).width(verticalSpacing.toInt())
	
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
	
	private fun setHorizontalSpacingOf(spacing: Float) =
		setDividerDrawable(drawableBuilder.width(spacing.toInt()).build())
	
	private fun setVerticalSpacingOf(spacing: Float) =
		setDividerDrawable(drawableBuilder.height(spacing.toInt()).build())
	
	private fun getAttributes(attrs: AttributeSet? = null, defStyleAttr: Int = 0) {
		context?.withStyledAttributes(attrs, defStyleAttr, R.styleable.AndreLinearLayout) {
			when (it) {
				R.styleable.AndreLinearLayout_android_horizontalSpacing ->
					horizontalSpacing = getDimension(it, horizontalSpacing)
				R.styleable.AndreLinearLayout_android_verticalSpacing ->
					verticalSpacing = getDimension(it, verticalSpacing)
			}
		}
	}
	
	fun setSpacing(spacing: Float) {
		horizontalSpacing = spacing
		verticalSpacing = spacing
	}
	
	init {
		setShowDivider(SHOW_DIVIDER_MIDDLE)
	}
}