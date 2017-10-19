package codemakers.daggermvvm.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import codemakers.daggermvvm.data.dao.TodoDao
import codemakers.daggermvvm.data.model.Todo
import java.util.*

/**
 * Created by Cristian on 17/10/2017.
 */
object Converters{

    @TypeConverter
    @JvmStatic
    fun dateToLong(date: Date): Long = date.time

    @TypeConverter
    @JvmStatic
    fun longToDate(timestamp: Long) = Date(timestamp)

}

@Database(entities = arrayOf(Todo::class), version = 1)

@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase(){

    abstract fun todoDao(): TodoDao
}