package com.jeanbarrossilva.andre.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jeanbarrossilva.andre.databinding.FragmentAreaComposerBinding
import com.jeanbarrossilva.andre.fragment.replacement.BindingFragment
import com.jeanbarrossilva.andre.viewmodel.AreaComposerViewModel
import com.jeanbarrossilva.andre.viewmodel.factory.AndreViewModelFactory.Companion.factoryOf

class AreaComposerFragment: BindingFragment<FragmentAreaComposerBinding>({ inflater, container ->
	FragmentAreaComposerBinding.inflate(inflater, container, false)
}) {
	private val viewModel by viewModels<AreaComposerViewModel> {
		factoryOf<AreaComposerViewModel>(this)
	}
	
	override fun onResume() {
		super.onResume()
		viewModel.configFab()
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.run {
			configSubareaFields()
			configAddSubareaButton()
		}
	}
	
	override fun onDestroy() {
		super.onDestroy()
		viewModel.closeKeyboard()
	}
}