package com.jeanbarrossilva.andre.activity.replacement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewbinding.ViewBinding
import com.jeanbarrossilva.andre.R

open class AndreActivity<VB: ViewBinding>(private val bindingBlock: LayoutInflater.() -> VB):
	AppCompatActivity() {
	lateinit var binding: VB
		private set
	
	private fun configNavigation() {
		findViewById<Toolbar>(R.id.toolbar)?.let { setSupportActionBar(it) }
		setupActionBarWithNavController(findNavController(R.id.container))
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = bindingBlock(layoutInflater)
		setContentView(binding.root)
		runCatching { configNavigation() }
	}
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == android.R.id.home)
			onBackPressed()
		return super.onOptionsItemSelected(item)
	}
}