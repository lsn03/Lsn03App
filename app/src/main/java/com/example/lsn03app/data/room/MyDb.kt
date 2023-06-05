package com.example.lsn03app.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lsn03app.data.room.dao.TaskDao
import com.example.lsn03app.data.room.dao.TaskListDao
import com.example.lsn03app.data.room.entity.TaskEntity
import com.example.lsn03app.data.room.entity.TaskListEntity


@Database(entities = [TaskListEntity::class,TaskEntity::class],version = 2)
abstract class MyDb : RoomDatabase() {
	abstract fun taskListDao(): TaskListDao
	abstract fun taskDao(): TaskDao

}