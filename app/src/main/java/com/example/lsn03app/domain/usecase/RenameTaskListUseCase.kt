package com.example.lsn03app.domain.usecase


import com.example.lsn03app.domain.ITaskListRepository
import com.example.lsn03app.domain.models.TaskList

class RenameTaskListUseCase (private  val repository: ITaskListRepository) {
	suspend fun execute(taskList: TaskList, newName:String){
		taskList.name = newName
		repository.updateTaskList(taskList);
	}
}