package com.jeanbarrossilva.andre.ui

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.extension.ContextX.colorOf
import com.jeanbarrossilva.andre.extension.ContextX.withStyledAttributes
import com.jeanbarrossilva.andre.extension.NumberX.dp
import com.jeanbarrossilva.andre.extension.ViewGroupX.chaining
import com.jeanbarrossilva.andre.extension.ViewX.setSize

class EmptyListView: AndreLinearLayout {
	private lateinit var headlineLayout: AndreLinearLayout
	private lateinit var iconView: ImageView
	private lateinit var titleView: TextView
	private lateinit var descriptionView: TextView
	
	var icon: Drawable? = null
		set(value) {
			field = value
			iconView.setImageDrawable(value)
		}
	var title = ""
		set(value) {
			field = value
			titleView.text = value
		}
	var description = ""
		set(value) {
			field = value
			descriptionView.text = value
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
	
	private fun initViews(attrs: AttributeSet?, defStyleAttr: Int) {
		val secondaryTextColor = context colorOf android.R.attr.textColorSecondary
		
		headlineLayout =
			AndreLinearLayout(context, attrs, defStyleAttr).apply {
				gravity = Gravity.CENTER
				orientation = VERTICAL
				verticalSpacing = 5.dp(context).toFloat()
				setPadding(30.dp(context), 0, 30.dp(context), 0)
			}
		iconView =
			ImageView(context, attrs, defStyleAttr).apply {
				imageTintList =
					ColorStateList.valueOf(secondaryTextColor)
				setSize(70.dp(context))
			}
		titleView =
			TextView(context, attrs, defStyleAttr).apply {
				textAlignment = TEXT_ALIGNMENT_CENTER
				textSize = 17f
				setTypeface(typeface, Typeface.BOLD)
				setTextColor(secondaryTextColor)
			}
		descriptionView =
			TextView(context, attrs, defStyleAttr).apply {
				textAlignment = TEXT_ALIGNMENT_CENTER
				setTextColor(secondaryTextColor)
			}
	}
	
	private fun addViews() {
		headlineLayout.addView(titleView)
		headlineLayout.addView(descriptionView)
		addView(iconView)
		addView(headlineLayout)
	}
	
	private fun getAttributes(attrs: AttributeSet?, defStyleAttr: Int) =
		context.withStyledAttributes(attrs, defStyleAttr, R.styleable.EmptyListView) {
			when (it) {
				R.styleable.EmptyListView_android_icon ->
					icon = getDrawable(it)
				R.styleable.EmptyListView_android_title ->
					title = getString(it).toString()
				R.styleable.EmptyListView_android_description ->
					description = getString(it).toString()
			}
		}
	
	private fun init(attrs: AttributeSet? = null, defStyleAttr: Int = 0) {
		initViews(attrs, defStyleAttr)
		addViews()
		getAttributes(attrs, defStyleAttr)
	}
	
	override fun setVisibility(visibility: Int) {
		super.setVisibility(visibility)
		chaining(isInclusive = false) { this.visibility = visibility }
	}
	
	init {
		gravity = Gravity.CENTER
		orientation = VERTICAL
		verticalSpacing = 15.dp(context).toFloat()
	}
}