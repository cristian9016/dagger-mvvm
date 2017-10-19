package codemakers.daggermvvm.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import codemakers.daggermvvm.ui.main.MainViewModel
import codemakers.daggermvvm.util.AppViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * Created by Cristian on 17/10/2017.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel:MainViewModel):ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory):ViewModelProvider.Factory

}