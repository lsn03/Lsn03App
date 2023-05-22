package com.example.lsn03app

import android.app.Application
import com.example.lsn03app.di.Dependencies


class App:Application() {
	override fun onCreate() {
		super.onCreate()
		Dependencies.init(applicationContext)
	}
}