package com.jeanbarrossilva.andre.interop

import android.view.View

interface AndreChart<V: View> {
	val view: V
	var showsEntryLabels: Boolean
	var showsEntryValues: Boolean
	var onSelectEntry: (AndreChartEntry) -> Unit
	
	fun setup()
	
	fun entries(): List<AndreChartEntry>
	
	fun add(entry: AndreChartEntry)
	
	fun add(entries: List<AndreChartEntry>) = entries.forEach { entry -> add(entry) }
}