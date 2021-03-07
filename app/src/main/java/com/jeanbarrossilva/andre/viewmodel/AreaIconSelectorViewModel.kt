package com.jeanbarrossilva.andre.viewmodel

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModel
import com.jeanbarrossilva.andre.fragment.AreaIconSelectorFragment

class AreaIconSelectorViewModel(private val fragment: AreaIconSelectorFragment): ViewModel() {
	fun configOnSelectIcon() =
		with(fragment.binding.iconsLayout) {
			setOnSelectIconListener { iconRes ->
				fragment.setFragmentResult(
					AreaIconSelectorFragment.KEY_ICON_SELECTED,
					bundleOf("icon" to iconRes)
				)
				fragment.dismiss()
			}
		}
}