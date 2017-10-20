package codemakers.daggermvvm.di

import android.arch.lifecycle.ViewModel
import codemakers.daggermvvm.di.modules.MainModule
import codemakers.daggermvvm.di.modules.ViewModelModule
import codemakers.daggermvvm.ui.add.AddActivity
import codemakers.daggermvvm.ui.main.MainActivity
import codemakers.daggermvvm.ui.update.UpdateActivity
import dagger.MapKey
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope
import kotlin.reflect.KClass


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
    abstract fun bindAddActivity(): AddActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindUpdateActivity(): UpdateActivity
}