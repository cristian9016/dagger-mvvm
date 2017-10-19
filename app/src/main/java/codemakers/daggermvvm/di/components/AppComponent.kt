package codemakers.daggermvvm.di.components

import android.app.Application
import codemakers.daggermvvm.App
import codemakers.daggermvvm.di.ActivityBuilders
import codemakers.daggermvvm.di.modules.AppModule
import codemakers.daggermvvm.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Cristian on 17/10/2017.
 */

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilders::class,
        ViewModelModule::class))
interface AppComponent{

    fun inject(app: App)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build():AppComponent
    }
}