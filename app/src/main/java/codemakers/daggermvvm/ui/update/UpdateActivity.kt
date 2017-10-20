package codemakers.daggermvvm.ui.update

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import javax.inject.Inject

import codemakers.daggermvvm.R
import codemakers.daggermvvm.data.model.Todo
import codemakers.daggermvvm.databinding.ActivityAddBinding
import codemakers.daggermvvm.databinding.ActivityUpdateBinding
import codemakers.daggermvvm.util.LifeDisposable
import codemakers.daggermvvm.util.text
import codemakers.daggermvvm.util.validateForm
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.text
import dagger.android.AndroidInjection
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    val dis:LifeDisposable = LifeDisposable(this)

    lateinit var binding : ActivityUpdateBinding

    @Inject
    lateinit var viewModel: UpdateViewModel

    lateinit var todo : Todo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_update)
        AndroidInjection.inject(this)
        todo = intent.getParcelableExtra("params")
        etUpdateDescripcion.setText(todo.description)
        supportActionBar?.setTitle(todo.title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onResume() {
        super.onResume()
        dis add btnUpdate.clicks()
                .flatMap { validateForm(R.string.message,etUpdateDescripcion.text()) }
                .flatMap { todo.description = it[0]
                    viewModel.updateTodo(todo)}
                .subscribeBy ( onNext = {
                    finish()
                } )
    }

}
