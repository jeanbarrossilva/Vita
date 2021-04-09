package com.jeanbarrossilva.andre.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.core.BottomSheetOption
import com.jeanbarrossilva.andre.fragment.replacement.OptionsBottomSheetFragment
import com.jeanbarrossilva.andre.viewmodel.AreaOptionsViewModel
import com.jeanbarrossilva.andre.viewmodel.factory.AndreViewModelFactory.Companion.factoryOf

class AreaOptionsBottomSheetFragment: OptionsBottomSheetFragment() {
	private val viewModel by viewModels<AreaOptionsViewModel> {
		factoryOf<AreaOptionsViewModel>(this)
	}
	private val navArgs by navArgs<AreaOptionsBottomSheetFragmentArgs>()
	
	internal val area by lazy { navArgs.area }
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setOptions(
			listOf(
				BottomSheetOption(
					context,
					R.drawable.ic_edit,
					R.string.AreaOptionsBottomSheetFragment_edit
				) { viewModel.onEdit() },
				BottomSheetOption(
					context,
					R.drawable.ic_delete,
					R.string.AreaOptionsBottomSheetFragment_delete,
				) { viewModel.onDelete() }
			)
		)
	}
}