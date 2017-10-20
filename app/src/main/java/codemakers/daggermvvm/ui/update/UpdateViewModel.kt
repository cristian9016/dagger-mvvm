package codemakers.daggermvvm.ui.update

import android.arch.lifecycle.ViewModel
import codemakers.daggermvvm.data.dao.TodoDao
import codemakers.daggermvvm.data.model.Todo
import codemakers.daggermvvm.util.ApplySchedules
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableError
import io.reactivex.internal.operators.observable.ObservableFromCallable
import javax.inject.Inject

/**
 * Created by Cristian on 19/10/2017.
 */
class UpdateViewModel @Inject constructor(val dao:TodoDao):ViewModel(){
    fun updateTodo(todo : Todo): Observable<Unit> {
        return ObservableFromCallable{dao.update(todo)}.
                ApplySchedules()
    }
}