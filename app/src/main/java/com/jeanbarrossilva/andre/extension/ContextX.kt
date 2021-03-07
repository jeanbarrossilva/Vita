package com.jeanbarrossilva.andre.extension

import android.content.Context
import android.content.SharedPreferences
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.StyleableRes
import androidx.core.content.edit
import androidx.core.content.res.use
import androidx.core.content.withStyledAttributes
import androidx.preference.PreferenceManager
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.extension.NumberX.to

object ContextX {
	private const val DEFAULT_TOAST_LENGTH = Toast.LENGTH_SHORT
	
	val Context.navigationBarColor get() = getColor(R.color.navigationBar)
	val Context.preferences: SharedPreferences
		get() = PreferenceManager.getDefaultSharedPreferences(this)
	val Context.primaryColor get() = getColor(R.color.primary)
	val Context.primaryTextColor get() = colorOf(android.R.attr.textColorPrimary)
	val Context.statusBarColor get() = getColor(R.color.statusBar)
	val Context.isSystemInLightTheme get() = resources.getBoolean(R.bool.isSystemInLightTheme)
	val Context.isSystemInDarkTheme get() = resources.getBoolean(R.bool.isSystemInDarkTheme)
	
	@PublishedApi
	internal inline infix fun <reified T : Number> Context.converting(
		conversion: Pair<Number, Int>
	): T {
		val (value, unit) = conversion
		return TypedValue.applyDimension(unit, value.toFloat(), resources.displayMetrics).to()
	}
	
	infix fun Context.colorOf(@AttrRes attrRes: Int) =
		obtainStyledAttributes(intArrayOf(attrRes)).use { it.getColor(0, Color.TRANSPARENT) }
	
	infix fun Context.drawableOf(@AttrRes attrRes: Int) =
		obtainStyledAttributes(intArrayOf(attrRes)).use { it.getDrawable(0) }
	
	fun Context.onFirstRun(block: () -> Unit) {
		val key = "isFirstRun"
		val isFirstRun = preferences.getBoolean(key, true)
		
		if (isFirstRun) {
			block()
			preferences.edit { putBoolean(key, false) }
		}
	}
	
	fun Context.toast(text: String, length: Int = DEFAULT_TOAST_LENGTH) =
		Toast.makeText(this, text, length).show()
	
	fun Context.withStyledAttributes(
		attrs: AttributeSet?,
		defStyleAttr: Int,
		@StyleableRes styleableRes: IntArray,
		onEachIndex: TypedArray.(Int) -> Unit
	) = withStyledAttributes(attrs, styleableRes, defStyleAttr, 0) {
		for (index in 0..indexCount)
			onEachIndex(this, index)
	}
}