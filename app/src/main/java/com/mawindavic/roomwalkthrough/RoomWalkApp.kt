package com.mawindavic.roomwalkthrough

import android.app.Application
import timber.log.Timber

class RoomWalkApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}