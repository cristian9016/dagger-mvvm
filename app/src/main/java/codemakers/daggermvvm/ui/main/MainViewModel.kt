package codemakers.daggermvvm.ui.main

import android.arch.lifecycle.ViewModel
import codemakers.daggermvvm.data.dao.TodoDao
import codemakers.daggermvvm.data.model.Todo
import codemakers.daggermvvm.util.ApplySchedules
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.observable.ObservableFromCallable
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class MainViewModel @Inject constructor(val dao: TodoDao): ViewModel(){

    fun listTodo():Flowable<List<Todo>> = dao.all()
            .ApplySchedules()

    fun deleteTodo(todo:Todo):Observable<Unit> =
            ObservableFromCallable{dao.delete(todo)}
                    .ApplySchedules()

    fun deshacerTodo(todo:Todo):Observable<Unit> =
            ObservableFromCallable{dao.insert(todo)}
                    .ApplySchedules()

    fun seleccionTodo(todo:Todo):Observable<Unit> =
            ObservableFromCallable{dao.update(todo)}
                    .ApplySchedules()

}
