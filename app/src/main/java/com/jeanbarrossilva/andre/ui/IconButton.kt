package com.jeanbarrossilva.andre.ui

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.extension.ContextX.colorOf
import com.jeanbarrossilva.andre.extension.ContextX.drawableOf
import com.jeanbarrossilva.andre.extension.ContextX.withStyledAttributes
import com.jeanbarrossilva.andre.extension.NumberX.dp

open class IconButton : CardView {
    private lateinit var iconLayout: FrameLayout
    private lateinit var iconView: ImageView

    var icon: Drawable? = null
        set(value) {
            field = value
            iconView.background = value
        }
    var iconTint = context colorOf R.attr.colorPrimary
        set(value) {
            field = value
            iconView.imageTintList = ColorStateList.valueOf(value)
        }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        super(context, attrs, defStyleAttr) {
            init(attrs, defStyleAttr)
        }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (child == iconLayout)
            super.addView(child, index, params)
        else
            throw IllegalStateException("Custom children are not allowed in an IconButton.")
    }

    override fun setBackground(background: Drawable?) {
        iconLayout.background = background
    }

    private fun initViews(attrs: AttributeSet?, defStyleAttr: Int) {
        iconLayout =
            FrameLayout(context, attrs, defStyleAttr).apply {
                background = context drawableOf android.R.attr.selectableItemBackground
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            }
        iconView =
            ImageView(context, attrs, defStyleAttr).apply {
                layoutParams = LayoutParams(24.dp(context), 24.dp(context)).apply {
                    gravity = Gravity.CENTER
                }
            }
    }

    private fun getAttributes(attrs: AttributeSet?, defStyleAttr: Int) =
        context.withStyledAttributes(attrs, defStyleAttr, R.styleable.IconButton) {
            when (it) {
                R.styleable.IconButton_android_icon -> icon = getDrawable(it)
                R.styleable.IconButton_android_iconTint -> iconTint = getColor(it, iconTint)
            }
        }

    private fun addViews() {
        addView(iconLayout)
        iconLayout.addView(iconView)
    }

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int = 0) {
        radius = 20.dp(context).toFloat()
        initViews(attrs, defStyleAttr).also {
            background = context drawableOf android.R.attr.selectableItemBackground
        }
        getAttributes(attrs, defStyleAttr)
        addViews()
    }

    fun setImageResource(@DrawableRes iconRes: Int, @ColorRes tint: Int) {
        this.icon = ContextCompat.getDrawable(context, iconRes)
        iconTint = tint
    }
}