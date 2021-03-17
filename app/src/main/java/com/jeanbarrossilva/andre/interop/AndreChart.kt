package com.jeanbarrossilva.andre.interop

import android.view.View

interface AndreChart<V: View> {
	val view: V
	var showsEntryLabels: Boolean
	var showsEntryValues: Boolean
	
	interface OnSelectEntryListener {
		fun onSelectEntry(entry: AndreChartEntry)
		
		companion object {
			operator fun invoke(block: (AndreChartEntry) -> Unit) =
				object: OnSelectEntryListener {
					override fun onSelectEntry(entry: AndreChartEntry) = block(entry)
				}
		}
	}
	
	fun add(entry: AndreChartEntry)
	
	fun add(entries: List<AndreChartEntry>) = entries.forEach { entry -> add(entry) }
	
	fun setOnSelectEntryListener(listener: OnSelectEntryListener)
	
	fun setOnSelectEntryListener(listener: (AndreChartEntry) -> Unit) =
		setOnSelectEntryListener(OnSelectEntryListener { listener(it) })
}