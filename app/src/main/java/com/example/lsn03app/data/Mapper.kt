package com.example.lsn03app.data

import com.example.lsn03app.data.room.entity.TaskEntity
import com.example.lsn03app.data.room.entity.TaskListEntity
import com.example.lsn03app.domain.models.Task
import com.example.lsn03app.domain.models.TaskList

class Mapper {
	 fun taskListToTaskListEntity(taskList: TaskList) : TaskListEntity {
		return TaskListEntity(taskList.id, taskList.name)
	}

	fun taskToTaskEntity(task: Task): TaskEntity {
		return TaskEntity(task.id,task.name,task.description,task.taskListId)
	}
	fun taskEntityToTask(task: TaskEntity): Task {
		return Task(task.name,task.description,task.taskListId,task.id)
	}
}