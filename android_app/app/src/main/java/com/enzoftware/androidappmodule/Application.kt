package com.enzoftware.androidappmodule

import android.app.Application
import com.enzoftware.androidappmodule.di.apiModule
import com.enzoftware.androidappmodule.di.appModule
import com.enzoftware.androidappmodule.di.networkModule
import com.enzoftware.androidappmodule.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


/**
 * Created by Enzo Lizama Paredes on 2020-02-16.
 * Contact: lizama.enzo@gmail.com
 */

class SimpsonsApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SimpsonsApplication)
            modules(listOf(appModule, apiModule, networkModule, viewModelModule))
        }
    }
}