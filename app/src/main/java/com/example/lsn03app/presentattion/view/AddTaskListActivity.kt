package com.example.lsn03app.presentattion.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.lsn03app.databinding.ActivityAddTaskListBinding
import com.example.lsn03app.di.Dependencies
import com.example.lsn03app.domain.models.TaskList
import com.example.lsn03app.presentattion.viewModel.MainViewModel

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTaskListActivity : AppCompatActivity() {
	lateinit var binding: ActivityAddTaskListBinding
	lateinit var taskListViewModel: MainViewModel
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityAddTaskListBinding.inflate(layoutInflater)
		setContentView(binding.root)

		taskListViewModel = ViewModelProvider(this)[MainViewModel::class.java]

		binding.add.setOnClickListener {
			val name = binding.name.text.toString()
			val isFavourite = binding.isFavouriteTaskList.isChecked

			taskListViewModel.addTaskList(TaskList(name,isFavourite))

			finish()
		}

	}
	companion object {
		fun getIntent(context: Context) : Intent {
			val intent = Intent(context, AddTaskListActivity::class.java)
			return intent
		}
	}
}

