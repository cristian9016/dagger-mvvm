package codemakers.daggermvvm.ui.add

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import codemakers.daggermvvm.R
import codemakers.daggermvvm.data.model.Todo
import codemakers.daggermvvm.databinding.ActivityAddBinding
import codemakers.daggermvvm.util.LifeDisposable
import codemakers.daggermvvm.util.validateForm
import codemakers.daggermvvm.util.text
import com.jakewharton.rxbinding2.view.clicks
import dagger.android.AndroidInjection
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_add.*
import javax.inject.Inject

class AddActivity : AppCompatActivity() {

    val dis: LifeDisposable = LifeDisposable(this)

    lateinit var binding: ActivityAddBinding

    @Inject
    lateinit var viewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add)
        AndroidInjection.inject(this)
    }

    override fun onResume() {
        super.onResume()

        dis add btnAdd.clicks()
                .flatMap { validateForm(R.string.message,etNombre.text(),etDescripcion.text()) }
                .flatMap { viewModel.AddTarea(it[0],it[1]) }
                .subscribeBy ( onNext={
                    finish()
                     }
                )
    }


}

