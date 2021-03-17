package com.jeanbarrossilva.andre.interop.implementation

import android.content.Context
import android.graphics.Color
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.MPPointF
import com.jeanbarrossilva.andre.extension.BooleanX.orFalse
import com.jeanbarrossilva.andre.extension.ChartX.setOnChartValueSelectedListener
import com.jeanbarrossilva.andre.extension.PieChartX.emptyData
import com.jeanbarrossilva.andre.interop.AndreChart
import com.jeanbarrossilva.andre.interop.AndreChartEntry

class AndrePieChart(context: Context): AndreChart<PieChart> {
	override val view =
		PieChart(context).apply {
			data = emptyData
			legend?.isEnabled = false
			description?.isEnabled = false
			isDrawHoleEnabled = false
			invalidate()
		}
	override var showsEntryLabels = view.isDrawEntryLabelsEnabled.orFalse()
		set(value) {
			field = value
			view.setDrawEntryLabels(value)
		}
	override var showsEntryValues = view.data?.dataSet?.isDrawValuesEnabled.orFalse()
		set(value) {
			field = value
			view.data.dataSet.valueTextColor = if (value) Color.WHITE else Color.TRANSPARENT
		}
	
	override fun setup() {
		val pieEntries =
			entries().map { it.toPieEntry() }.also {
				for (entry in it)
					entry.icon?.setTint(Color.WHITE)
			}
		val dataSet =
			PieDataSet(pieEntries, "").apply {
				colors = entries().map { it.color }
			}
		
		view.data = PieData(dataSet)
		view.data.dataSet.iconsOffset = MPPointF(-20f, 0f)
	}
	
	override fun entries(): List<AndreChartEntry> {
		val entries = mutableListOf<PieEntry>()
		val dataSet = view.data.dataSet
		val entryCount = dataSet.entryCount
		
		if (entryCount > 0)
			for (index in 0..entryCount) {
				val entry = dataSet.getEntryForIndex(index)
				entries.add(entry!!)
			}
		return entries.map { it.toAndreChartEntry() }
	}
	
	override fun add(entry: AndreChartEntry) {
		view.data.dataSet.colors.add(entry.color)
		view.data.dataSet.addEntry(entry.toPieEntry())
		view.invalidate()
	}
	
	override fun setOnSelectEntryListener(listener: AndreChart.OnSelectEntryListener) {
		view.setOnChartValueSelectedListener { entry, _ ->
			listener.onSelectEntry((entry as PieEntry).toAndreChartEntry())
		}
	}
	
	private fun AndreChartEntry.toPieEntry() =
		PieEntry(value, title, icon).apply { icon.setTint(Color.WHITE) }
	
	private fun PieEntry.toAndreChartEntry(): AndreChartEntry {
		val color = view.data.dataSet.colors[view.data.dataSet.getEntryIndex(this)]!!
		return AndreChartEntry(label, icon, value, color)
	}
	
	init {
		setup()
	}
}