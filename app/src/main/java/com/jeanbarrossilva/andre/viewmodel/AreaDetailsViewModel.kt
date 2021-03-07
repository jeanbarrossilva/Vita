package com.jeanbarrossilva.andre.viewmodel

import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.jeanbarrossilva.andre.R
import com.jeanbarrossilva.andre.extension.ContextX.drawableOf
import com.jeanbarrossilva.andre.extension.ContextX.primaryTextColor
import com.jeanbarrossilva.andre.extension.MaterialButtonX.setTint
import com.jeanbarrossilva.andre.extension.MutableLiveDataX.toggle
import com.jeanbarrossilva.andre.extension.WindowX.insetsControllerCompat
import com.jeanbarrossilva.andre.fragment.AreaDetailsFragment
import com.jeanbarrossilva.andre.fragment.AreaDetailsFragmentArgs

class AreaDetailsViewModel(private val fragment: AreaDetailsFragment) : ViewModel() {
    private val navArgs by fragment.navArgs<AreaDetailsFragmentArgs>()
    private val area by lazy { navArgs.area }
    private val isEditing = MutableLiveData(false)

    private fun showIconPicker() {
    }

    fun showDetails() =
        with(fragment.binding) {
            iconButton.iconTint = area.color
            iconButton.setImageResource(area.iconRes, area.color)
            nameFieldLayout.field.setText(area.name)
            descriptionFieldLayout.field.setText(area.description)
            attentionLevelIndicator.progress = area.attentionLevel.toFloat()
            attentionLevelIndicator.progressColor = area.color
        }

    fun configEditingState() =
        with(fragment.binding) {
            isEditing.observe(fragment) { isEditing ->
                val iconButtonBackground =
                    if (isEditing) root.context drawableOf android.R.attr.selectableItemBackground
                    else null
                val onIconButtonClick = { if (isEditing) showIconPicker() }
                val editButtonIconRes = if (isEditing) R.drawable.ic_done else R.drawable.ic_edit
                val editButtonTextRes = if (isEditing) R.string.AreaDetailsFragment_done else
                    R.string.AreaDetailsFragment_edit
                val editButtonTint =
                    with(fragment.requireContext()) {
                        if (isEditing) area.color else primaryTextColor
                    }
                
                iconButton.iconTint = area.color
                iconButton.background = iconButtonBackground
                iconButton.setOnClickListener { onIconButtonClick() }
                iconButton.setImageResource(area.iconRes, area.color)
                nameFieldLayout.isEnabled = isEditing
                nameFieldLayout.setTint(area.color)
                descriptionFieldLayout.isEnabled = isEditing
                descriptionFieldLayout.setTint(area.color)
                editButton.text = fragment.context?.getString(editButtonTextRes)
                editButton.setIconResource(editButtonIconRes)
                editButton.setTint(editButtonTint)
                editButton.setOnClickListener { this@AreaDetailsViewModel.isEditing.toggle() }
            }
        }
    
    fun hideSoftInput() {
        fragment.activity?.window?.insetsControllerCompat?.hide(ime())
    }
}