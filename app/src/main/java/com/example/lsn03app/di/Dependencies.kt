package com.example.lsn03app.di

import android.content.Context
import androidx.room.Room
import com.example.lsn03app.data.TaskListRepository
import com.example.lsn03app.data.TaskRepository
import com.example.lsn03app.data.room.MyDb

object Dependencies {

	lateinit var context: Context;

	fun init(context: Context){
		this.context = context;
	}

	private val db by lazy {
		Room.databaseBuilder(context,MyDb::class.java,"task.db")
			.fallbackToDestructiveMigration()
			.createFromAsset("database/task_db.db")
			.build()
	}
	val taskListRepository by lazy {
		TaskListRepository(db.taskListDao())
	}
	val taskRepository by lazy {
		TaskRepository(db.taskDao())
	}
}