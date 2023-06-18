package com.example.lsn03app.domain.models

import java.text.SimpleDateFormat
import java.util.*

data class Task(

	val name: String,
	val description: String,
	val taskListId: Int,
	val isFavourite: Boolean = false,
	val id: Int = UNDEFINED_ID,
	val createDate: String = CURRENT_TIME,

	) {
	companion object {
		const val UNDEFINED_ID = 0;
		val CURRENT_TIME by lazy { getCurrentDateTime() }

		private fun getCurrentDateTime(): String {

			val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
			val curDate = Date()

			dateFormat.timeZone = TimeZone.getTimeZone("GMT+10")
			return dateFormat.format(curDate)


		}
	}
}
