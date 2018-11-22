package com.adrianbukros.github.example

import android.app.Application
import timber.log.LogcatTree
import timber.log.Timber

class GitHubApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(LogcatTree())
    }
}
