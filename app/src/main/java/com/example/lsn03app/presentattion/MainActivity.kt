package com.example.lsn03app.presentattion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import androidx.lifecycle.ViewModelProvider
import com.example.lsn03app.databinding.ActivityMainBinding
import com.example.lsn03app.di.Dependencies
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import java.io.Console

class MainActivity : AppCompatActivity() {

	lateinit var binding: ActivityMainBinding
	lateinit var vpAdapter: ViewPagerAdapter
	lateinit var vm: MainViewModel
	lateinit var vmTaskList: TaskListViewModel
	var tabIndex : Int = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		vm = ViewModelProvider(this)[MainViewModel::class.java]
		vmTaskList = TaskListViewModel()
		Dependencies.taskRepository

		if (tabIndex == 0){

		// если мы на фаворите то таски не можем добавлять
			//
		}else{

		}

		vm.taskLists.observe(this){
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
				vm.taskLists.value?.get(tabIndex)?.let {
						it1 -> TaskActivity.getIntent(this, it1.id)
				}
			)
		}


		vm.getAllTaskList()

		binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener{
			override fun onTabSelected(tab: TabLayout.Tab?) {
				tabIndex = tab!!.position
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
		vm.getAllTaskList()
	}
}