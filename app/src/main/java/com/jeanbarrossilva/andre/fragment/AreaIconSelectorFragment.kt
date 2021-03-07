package com.jeanbarrossilva.andre.fragment

import androidx.fragment.app.viewModels
import com.jeanbarrossilva.andre.databinding.FragmentAreaIconSelectorBinding
import com.jeanbarrossilva.andre.fragment.replacement.AndreDialogFragment
import com.jeanbarrossilva.andre.viewmodel.AreaIconSelectorViewModel
import com.jeanbarrossilva.andre.viewmodel.factory.AndreViewModelFactory.Companion.factoryOf

class AreaIconSelectorFragment: AndreDialogFragment<FragmentAreaIconSelectorBinding>({
	FragmentAreaIconSelectorBinding.inflate(this)
}) {
	private val viewModel by viewModels<AreaIconSelectorViewModel> {
		factoryOf<AreaIconSelectorViewModel>(this)
	}
	
	override fun onResume() {
		super.onResume()
		viewModel.configOnSelectIcon()
	}
	
	companion object {
		const val KEY_ICON_SELECTED = "iconSelected"
	}
}