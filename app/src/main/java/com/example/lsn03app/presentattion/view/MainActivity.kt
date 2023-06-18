package com.example.lsn03app.presentattion.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import androidx.lifecycle.ViewModelProvider
import com.example.lsn03app.databinding.ActivityMainBinding
import com.example.lsn03app.di.Dependencies
import com.example.lsn03app.presentattion.viewModel.MainViewModel
import com.example.lsn03app.presentattion.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

	lateinit var binding: ActivityMainBinding
	lateinit var vpAdapter: ViewPagerAdapter
	lateinit var mainViewModel: MainViewModel

	var tabIndex : Int = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

		Dependencies.taskRepository

		mainViewModel.taskLists.observe(this){
			vpAdapter = ViewPagerAdapter(this, it)
			binding.viewPager.adapter = vpAdapter
			TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
				tab.text = it[pos].name
			}.attach()

		}

		binding.addTaskListButton.setOnClickListener {
			startActivity(
				AddTaskListActivity.getIntent(this)
			)
		}

		binding.addTaskInTaskListButton.setOnClickListener {
			startActivity(
				mainViewModel.taskLists.value?.get(tabIndex)?.let {
						it1 ->
					TaskActivity.getIntent(this, it1.id)
				}
			)
		}

		binding.removeTaskListButton.setOnClickListener {
			val taskListId = mainViewModel.taskLists.value?.get(tabIndex)?.id
			mainViewModel.taskLists.value?.toMutableList()?.removeAt(tabIndex)
			taskListId?.let { it1 -> mainViewModel.removeTaskList(it1) }

		}
		mainViewModel.getAllTaskList()

		binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener{
			override fun onTabSelected(tab: TabLayout.Tab?) {
				tabIndex = tab!!.position
				if (tabIndex == 0){
					binding.addTaskInTaskListButton.visibility = View.INVISIBLE
					binding.removeTaskListButton.visibility = View.INVISIBLE

				}else{
					binding.addTaskInTaskListButton.visibility = View.VISIBLE
					binding.removeTaskListButton.visibility = View.VISIBLE
				}
			}

			override fun onTabUnselected(tab: TabLayout.Tab?) {
				return
			}

			override fun onTabReselected(tab: TabLayout.Tab?) {
				return
			}
		})


	}
	override fun onResume() {
		super.onResume()
		mainViewModel.getAllTaskList()
	}
}