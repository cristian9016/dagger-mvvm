package codemakers.daggermvvm.ui.main

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import codemakers.daggermvvm.R
import codemakers.daggermvvm.data.model.Todo
import codemakers.daggermvvm.databinding.ActivityMainBinding
import codemakers.daggermvvm.ui.adapters.TodoAdapter
import codemakers.daggermvvm.ui.add.AddActivity
import codemakers.daggermvvm.ui.update.UpdateActivity
import codemakers.daggermvvm.util.ApplySchedules
import codemakers.daggermvvm.util.LifeDisposable
import codemakers.daggermvvm.util.snackBarAction
import com.jakewharton.rxbinding2.view.clicks
import dagger.android.AndroidInjection
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val dis: LifeDisposable = LifeDisposable(this)

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var adapter: TodoAdapter

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        AndroidInjection.inject(this)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this)
        supportActionBar?.setTitle("Tareas")
    }

    override fun onResume() {
        super.onResume()

        dis add add.clicks().subscribe { startActivity<AddActivity>() }

        dis add viewModel.listTodo().subscribe { adapter.data = it }

        dis add adapter.clearSubject
                .flatMap { todo -> viewModel.deleteTodo(todo).map { todo } }
                .flatMap { snackBarAction(contentView!!, R.string.deleted, R.string.deshacer, it) }
                .flatMap { viewModel.deshacerTodo(it as Todo) }
                .subscribe()

        dis add adapter.update
                .subscribe { startActivity<UpdateActivity>("params" to it) }


    }
}
