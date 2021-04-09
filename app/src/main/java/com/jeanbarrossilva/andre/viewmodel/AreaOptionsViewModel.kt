package com.jeanbarrossilva.andre.viewmodel

import androidx.lifecycle.ViewModel
import com.afollestad.materialdialogs.MaterialDialog
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.extension.MaterialDialogX.title
import com.jeanbarrossilva.andre.fragment.AreaOptionsBottomSheetFragment
import com.jeanbarrossilva.andre.repo.AreaRepository

class AreaOptionsViewModel(private val fragment: AreaOptionsBottomSheetFragment): ViewModel() {
	fun onEdit() {
	}
	
	fun onDelete() {
		MaterialDialog(fragment.requireContext()).show {
			title(
				R.string.AreaOptionsBottomSheetFragment_dialog_title_delete,
				format = arrayOf(fragment.area.title)
			)
			message(R.string.AreaOptionsBottomSheetFragment_dialog_message_delete)
			positiveButton { AreaRepository.remove(fragment.area) }
			negativeButton { dismiss() }
		}
	}
}