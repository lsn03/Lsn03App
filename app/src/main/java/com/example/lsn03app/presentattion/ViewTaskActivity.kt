package com.example.lsn03app.presentattion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lsn03app.databinding.ActivityViewTaskBinding

class ViewTaskActivity : AppCompatActivity() {
	lateinit var binding : ActivityViewTaskBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityViewTaskBinding.inflate(layoutInflater)

		setContentView(binding.root)
	}
	companion object{
		fun getIntent(title:String,desc:String,isFavourite:Boolean){

		}
	}
}