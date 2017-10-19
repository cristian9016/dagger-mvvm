package codemakers.daggermvvm.di.modules

import android.app.Activity
import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import codemakers.daggermvvm.data.AppDataBase
import codemakers.daggermvvm.data.dao.TodoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Cristian on 17/10/2017.
 */
@Module
class AppModule{

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    fun providePreference(context: Context): SharedPreferences =
            context.getSharedPreferences("TodoPreference", Activity.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDataBase =
            Room.databaseBuilder(context, AppDataBase::class.java, "todo.db")
                    .build()

    @Singleton
    @Provides
    fun provideTodoDao(appDataBase: AppDataBase): TodoDao = appDataBase.todoDao()
}