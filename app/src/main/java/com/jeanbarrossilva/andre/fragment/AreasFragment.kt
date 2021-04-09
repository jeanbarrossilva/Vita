package com.jeanbarrossilva.andre.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jeanbarrossilva.andre.databinding.FragmentAreasBinding
import com.jeanbarrossilva.andre.fragment.replacement.BindingFragment
import com.jeanbarrossilva.andre.viewmodel.AreasViewModel
import com.jeanbarrossilva.andre.viewmodel.factory.AndreViewModelFactory.Companion.factoryOf

class AreasFragment: BindingFragment<FragmentAreasBinding>({ inflater, container ->
	FragmentAreasBinding.inflate(inflater, container, false)
}) {
	private val viewModel by viewModels<AreasViewModel> { factoryOf<AreasViewModel>(this) }
	
	override fun onResume() {
		super.onResume()
		viewModel.configFab()
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.run {
			configEmptyListViewVisibility()
			showAreas()
		}
	}
}