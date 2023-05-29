package com.example.lsn03app.presentattion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.lsn03app.databinding.ActivityMainBinding
import com.example.lsn03app.di.Dependencies
import com.example.lsn03app.domain.models.TaskList
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

	lateinit var binding: ActivityMainBinding
	lateinit var vpAdapter: ViewPagerAdapter
	lateinit var vm: MainViewModel


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		vm = ViewModelProvider(this)[MainViewModel::class.java]

		Dependencies.taskRepository

		vm.taskLists.observe(this){
			binding.tabLayout.removeAllTabs()
			for (taskList in it) {
				binding.tabLayout.addTab(binding.tabLayout.newTab().setText(taskList.name))
			}
			vpAdapter = ViewPagerAdapter(this, it)
			binding.viewPager.adapter = vpAdapter
		}

		binding.addTaskListButton.setOnClickListener {
			vm.addTaskList("TEST: ${Random.nextInt()}")
		}

		vm.getAllTaskList()



	}
}