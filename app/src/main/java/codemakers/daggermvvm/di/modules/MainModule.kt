package codemakers.daggermvvm.di.modules

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import codemakers.daggermvvm.di.ActivityScope
import codemakers.daggermvvm.ui.adapters.TodoAdapter
import codemakers.daggermvvm.ui.main.MainActivity
import codemakers.daggermvvm.ui.main.MainViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Cristian on 17/10/2017.
 */
@Module
class MainModule{

    @ActivityScope
    @Provides
    fun provideViewModel(activity:MainActivity,factory: ViewModelProvider.Factory):MainViewModel
        = ViewModelProviders.of(activity,factory).get(MainViewModel::class.java)

    @Provides
    fun provideAdapter(): TodoAdapter = TodoAdapter()
}