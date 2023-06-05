package com.example.lsn03app.domain.models

data class TaskList(

	var name:String,
	val isFavourite:Boolean = false,
	val id:Int = UNDEFINED_ID,

){
	companion object{
		const val UNDEFINED_ID = 0
	}
}
