package codemakers.daggermvvm.ui.add

import android.arch.lifecycle.ViewModel
import codemakers.daggermvvm.data.dao.TodoDao
import codemakers.daggermvvm.data.model.Todo
import codemakers.daggermvvm.util.ApplySchedules
import javax.inject.Inject
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableFromCallable
import java.util.*

/**
 * Created by Cristian on 18/10/2017.
 */
class AddViewModel @Inject constructor(val dao:TodoDao):ViewModel(){

    fun AddTarea(tarea:String,descripcion:String):Observable<Unit>{
        return ObservableFromCallable{dao.insert(Todo(null,tarea, descripcion, Date()))}
                .ApplySchedules()
    }

}