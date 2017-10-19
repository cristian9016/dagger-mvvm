package codemakers.daggermvvm

import android.app.Activity
import android.app.Application
import codemakers.daggermvvm.di.components.AppComponent
import codemakers.daggermvvm.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Cristian on 17/10/2017.
 */
class App: Application(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity>
            = injector


    val appComponent: AppComponent by lazy {

        DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}