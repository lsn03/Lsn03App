package com.example.lsn03app.presentattion.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
	private lateinit var mode: String
	val taskListID by lazy { intent.getIntExtra("taskListId", 0) }
	val taskListName by lazy { intent.getStringExtra("taskListName") }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityAddTaskListBinding.inflate(layoutInflater)
		setContentView(binding.root)

		taskListViewModel = ViewModelProvider(this)[MainViewModel::class.java]

		mode = intent.getStringExtra("mode").toString()

		if (mode == "CREATE") {
			binding.add.visibility = View.VISIBLE
			binding.save.visibility = View.GONE
		} else if (mode == "EDIT") {
			binding.add.visibility = View.GONE
			binding.save.visibility = View.VISIBLE
			binding.name.setText(taskListName)
		}
		binding.save.setOnClickListener {
			val name = binding.name.text.toString()

			taskListViewModel.renameTaskList(
				name,
				TaskList(taskListName.toString(), id = taskListID)
			)
			finish()
		}
		binding.add.setOnClickListener {
			val name = binding.name.text.toString()
			val isFavourite = binding.isFavouriteTaskList.isChecked

			taskListViewModel.addTaskList(TaskList(name, isFavourite))

			finish()
		}

	}

	companion object {
		fun getIntent(context: Context): Intent {
			val intent = Intent(context, AddTaskListActivity::class.java)
			intent.putExtra("mode", "CREATE")
			return intent
		}

		fun getIntent(context: Context, taskList: TaskList): Intent {
			val intent = Intent(context, AddTaskListActivity::class.java)
			intent.putExtra("taskListId", taskList.id)
			intent.putExtra("taskListName", taskList.name)

			intent.putExtra("mode", "EDIT")
			return intent
		}
	}
}

