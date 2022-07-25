package com.yongjincompany.dsmtcg.di

import android.app.Application
import androidx.work.Configuration
import com.jakewharton.threetenabp.AndroidThreeTen
import com.yongjincompany.dsmtcg.util.DSMTCGExceptionHandler
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DSMTCGApplication : Application(), Configuration.Provider {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        setCrashHandler()
    }

    private fun setCrashHandler() {
        val crashlyticsExceptionHandler = Thread.getDefaultUncaughtExceptionHandler() ?: return
        Thread.setDefaultUncaughtExceptionHandler(
            DSMTCGExceptionHandler(
                this,
                crashlyticsExceptionHandler
            )
        )
    }

    override fun getWorkManagerConfiguration(): Configuration {
        TODO("Not yet implemented")
    }

}