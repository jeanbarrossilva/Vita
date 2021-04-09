package com.jeanbarrossilva.andre.ui

import android.content.Context
import android.util.AttributeSet
import android.view.Menu
import android.widget.PopupMenu
import androidx.core.content.res.getResourceIdOrThrow
import com.google.android.material.button.MaterialButton
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.extension.ContextX.withStyledAttributes

class DropdownButton: MaterialButton {
	private val popupMenu = PopupMenu(context, this)
	private var isMenuShowing = false
	
	val menu: Menu = popupMenu.menu
	
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
	
	private fun getAttributes(attrs: AttributeSet? = null, defStyleAttr: Int = 0) =
		context.withStyledAttributes(attrs, defStyleAttr, R.styleable.DropdownButton) {
			if (it == R.styleable.DropdownButton_menu)
				runCatching { popupMenu.menuInflater?.inflate(getResourceIdOrThrow(it), menu) }
		}
	
	private fun observeMenuVisibility() {
		popupMenu.setOnDismissListener {
			isMenuShowing = false
			setIconResource(R.drawable.ic_arrow_drop_down)
		}
	}
	
	init {
		iconGravity = ICON_GRAVITY_END
		observeMenuVisibility()
		setIconResource(R.drawable.ic_arrow_drop_down)
		setOnClickListener {
			if (!isMenuShowing) {
				isMenuShowing = true
				popupMenu.show()
				setIconResource(R.drawable.ic_arrow_drop_up)
			}
		}
	}
}