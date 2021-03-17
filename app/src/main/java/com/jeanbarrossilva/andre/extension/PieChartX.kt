package com.jeanbarrossilva.andre.extension

import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet

object PieChartX {
	fun PieChart.setEmptyData() {
		data = PieData(PieDataSet(mutableListOf(), ""))
	}
}