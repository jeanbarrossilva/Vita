package com.jeanbarrossilva.andre.fragment.replacement

import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.core.view.setPadding
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.extension.ViewX.rootWindowInsetsCompat
import com.jeanbarrossilva.andre.extension.WindowX.insetsControllerCompat

open class AndreDialogFragment<VB: ViewBinding>(bindingBlock: LayoutInflater.() -> VB):
	BindingDialogFragment<VB>(bindingBlock) {
	private val onShowListeners = mutableListOf<DialogInterface.OnShowListener>()
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val defaultPadding =
			context?.resources?.getDimension(R.dimen.andre_dialog_fragment_default_padding)?.toInt()
				?: 0
		return super.onCreateView(inflater, container, savedInstanceState).apply {
			setPadding(defaultPadding)
		}
	}
	
	override fun onCreateDialog(savedInstanceState: Bundle?) =
		super.onCreateDialog(savedInstanceState).apply {
			setOnShowListener {
				window?.attributes?.gravity = Gravity.CENTER
				window?.setLayout(context.resources.displayMetrics.widthPixels - 100, WRAP_CONTENT)
				window?.setBackgroundDrawableResource(R.drawable.bg_andre_dialog_fragment)
			}
		}
	
	override fun show(manager: FragmentManager, tag: String?) {
		super.show(manager, tag)
		triggerOnShowListeners()
	}
	
	override fun showNow(manager: FragmentManager, tag: String?) {
		super.showNow(manager, tag)
		triggerOnShowListeners()
	}
	
	private fun triggerOnShowListeners() = onShowListeners.forEach { it.onShow(dialog) }
	
	fun setOnShowListener(listener: DialogInterface.OnShowListener) = onShowListeners.add(listener)
	
	init {
		setOnShowListener {
			if (dialog?.window?.decorView?.rootWindowInsetsCompat?.isVisible(ime()) == true)
				dialog?.window?.insetsControllerCompat?.hide(ime())
		}
	}
}