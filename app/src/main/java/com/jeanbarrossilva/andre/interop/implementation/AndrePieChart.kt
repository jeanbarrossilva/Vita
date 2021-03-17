package com.jeanbarrossilva.andre.interop.implementation

import android.content.Context
import android.graphics.Color
import android.util.Log
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.MPPointF
import com.jeanbarrossilva.andre.extension.BooleanX.orFalse
import com.jeanbarrossilva.andre.extension.ChartX.setOnChartValueSelectedListener
import com.jeanbarrossilva.andre.extension.PieChartX.setEmptyData
import com.jeanbarrossilva.andre.interop.AndreChart
import com.jeanbarrossilva.andre.interop.AndreChartEntry

class AndrePieChart(context: Context): AndreChart<PieChart> {
	override val view =
		PieChart(context).apply {
			setEmptyData()
			data.dataSet.colors.clear()
			data.dataSet.iconsOffset = MPPointF(-20f, 0f)
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
	
	override fun add(entry: AndreChartEntry) {
		view.data.dataSet.colors.add(entry.color)
		Log.d("AndrePieChart.add", "Color after addition: ${view.data.dataSet.colors}")
		view.data.dataSet.addEntry(entry.toPieEntry())
		view.invalidate()
	}
	
	override fun setOnSelectEntryListener(listener: AndreChart.OnSelectEntryListener) {
		view.setOnChartValueSelectedListener { entry, _ ->
			val andreChartEntry = (entry as PieEntry).toAndreChartEntry()
			listener.onSelectEntry(andreChartEntry)
		}
	}
	
	private fun AndreChartEntry.toPieEntry() =
		PieEntry(value, title, icon).apply { icon.setTint(Color.WHITE) }
	
	private fun PieEntry.toAndreChartEntry(): AndreChartEntry {
		val color = view.data.dataSet.colors[view.data.dataSet.getEntryIndex(this)]!!
		return AndreChartEntry(label, icon, value, color)
	}
}