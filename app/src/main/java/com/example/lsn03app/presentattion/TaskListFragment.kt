package com.example.lsn03app.presentattion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.lsn03app.databinding.FragmentTaskListBinding




class TaskListFragment(private val taskListId:Int) : Fragment() {
	private lateinit var viewModel: TaskListViewModel
	private lateinit var binding: FragmentTaskListBinding
	private lateinit var mainViewModel: MainViewModel


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentTaskListBinding.inflate(layoutInflater)
		viewModel = ViewModelProvider(this)[TaskListViewModel::class.java]
		mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val adapter = TaskListAdapter()

		binding.rv.adapter = adapter
		binding.rv.layoutManager = LinearLayoutManager(requireActivity())
		viewModel.list.observe(viewLifecycleOwner){
			adapter.submitList(it)
		}

		val callback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
			override fun onMove(
				recyclerView: RecyclerView,
				viewHolder: RecyclerView.ViewHolder,
				target: RecyclerView.ViewHolder
			): Boolean {
				adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
				return true
			}

			override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

			}
		}

		val itemTouchHelper = ItemTouchHelper(callback).attachToRecyclerView(binding.rv)
		if(taskListId == 1){
			viewModel.getFavouriteTasks()
		}else{
			viewModel.getTasksFromTaskList(taskListId)
		}

	}


}