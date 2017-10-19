package codemakers.daggermvvm.ui.adapters

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import codemakers.daggermvvm.R
import codemakers.daggermvvm.data.model.Todo
import codemakers.daggermvvm.databinding.TemplateTodoBinding
import codemakers.daggermvvm.util.inflate
import io.reactivex.subjects.PublishSubject
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Cristian on 17/10/2017.
 */

class TodoAdapter: RecyclerView.Adapter<TodoAdapter.TodoHolder>() {

    var data:List<Todo> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    val clearSubject: PublishSubject<Todo> = PublishSubject.create()

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.binding.todo = data[position]
        holder.binding.clear = clearSubject
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder
            = TodoHolder(parent.inflate(R.layout.template_todo))

    override fun getItemCount(): Int = data.size

    class TodoHolder(view: View): RecyclerView.ViewHolder(view){
        val binding: TemplateTodoBinding = DataBindingUtil.bind(view)
    }

    companion object {

        private val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())

        @JvmStatic
        @BindingAdapter("app:dateFormat")
        fun applyFormat(textView: TextView, date: Date){
            textView.text = format.format(date)
        }
    }

}