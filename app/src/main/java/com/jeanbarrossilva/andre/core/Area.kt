package com.jeanbarrossilva.andre.core

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Architecture
import androidx.compose.material.icons.rounded.EscalatorWarning
import androidx.compose.material.icons.rounded.FamilyRestroom
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.jeanbarrossilva.andre.R
import java.io.Serializable

@Suppress("Unused")
sealed class Area(
	imageVector: ImageVector,
	val title: String,
	val color: Color,
	val subareas: List<Subarea>
): Serializable {
	val icon = AndreIcon(imageVector, "$title icon")

	class LifeQuality(context: Context):
		Area(
			Icons.Rounded.FamilyRestroom,
			title = context.getString(R.string.Area_name_life_quality),
			Color(0x00F57C00),
			listOf(
				Subarea.Health(context),
				Subarea.Lounge(context),
				Subarea.Spirituality(context)
			)
		)
	
	class Personal(context: Context):
		Area(
			Icons.Rounded.Person,
			title = context.getString(R.string.Area_name_personal),
			Color(0x00EF233C),
			listOf(
				Subarea.Emotional(context),
				Subarea.Happiness(context),
				Subarea.Intellect(context),
			)
		)
	
	class Professional(context: Context):
		Area(
			Icons.Rounded.Architecture,
			title = context.getString(R.string.Area_name_professional),
			Color(0x004895EF),
			listOf(
				Subarea.Career(context),
				Subarea.Contribution(context),
				Subarea.Finances(context)
			)
		)
	
	class Relationships(context: Context):
		Area(
			Icons.Rounded.EscalatorWarning,
			title = context.getString(R.string.Area_name_relationships),
			Color(0x0074C69D),
			listOf(
				Subarea.Family(context),
				Subarea.Love(context),
				Subarea.Social(context)
			)
		)
	
	companion object {
		fun values(context: Context) =
			listOf(
				LifeQuality(context),
				Personal(context),
				Professional(context),
				Relationships(context)
			)
	}
}