package codemakers.daggermvvm.di

import android.arch.lifecycle.ViewModel
import codemakers.daggermvvm.di.modules.MainModule
import codemakers.daggermvvm.di.modules.ViewModelModule
import codemakers.daggermvvm.ui.add.AddActivity
import codemakers.daggermvvm.ui.main.MainActivity
import dagger.MapKey
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope
import kotlin.reflect.KClass

/**
 * Created by Cristian on 17/10/2017.
 */


@Retention(AnnotationRetention.RUNTIME)
@Scope
annotation class ActivityScope

@Module
abstract class ActivityBuilders{


    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindAAddActivity(): AddActivity
}