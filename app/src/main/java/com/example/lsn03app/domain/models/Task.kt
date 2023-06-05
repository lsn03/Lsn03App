package com.example.lsn03app.domain.models

data class Task (

	val name:String,
	val description :String,
	val taskListId:Int,
 	val id:Int = UNDEFINED_ID,
	val isFavourite:Boolean = false,
){
	companion object{
		const val UNDEFINED_ID = 0
	}
}
