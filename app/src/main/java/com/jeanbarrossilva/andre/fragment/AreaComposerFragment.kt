package com.jeanbarrossilva.andre.fragment

import androidx.fragment.app.viewModels
import com.jeanbarrossilva.andre.databinding.FragmentAreaComposerBinding
import com.jeanbarrossilva.andre.fragment.replacement.BindingFragment
import com.jeanbarrossilva.andre.viewmodel.AreaComposerViewModel
import com.jeanbarrossilva.andre.viewmodel.factory.AndreViewModelFactory.Companion.factoryOf

class AreaComposerFragment:
	BindingFragment<FragmentAreaComposerBinding>({ FragmentAreaComposerBinding.inflate(this) }) {
	private val viewModel by viewModels<AreaComposerViewModel> {
		factoryOf<AreaComposerViewModel>(this)
	}
	
	override fun onResume() {
		super.onResume()
		viewModel.run {
			configFab()
			focusPrimaryField()
			configFields()
			configIconButton()
			waitForIconSelection()
		}
	}
	
	override fun onDestroy() {
		super.onDestroy()
		viewModel.hideSoftInput()
	}
}