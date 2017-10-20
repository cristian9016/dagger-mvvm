package codemakers.daggermvvm.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by Cristian on 17/10/2017.
 */
@Parcelize
@Entity
class Todo(@PrimaryKey var id: Long?,
           var title: String?,
           var description: String?,
           var date: Date):Parcelable{

    @Ignore
    constructor():this(null,null,null,Date())

    @Ignore
    constructor(title: String, description: String?) : this(null, title, description, Date())

    companion object : Parceler<Todo>{
        override fun create(parcel: Parcel): Todo = Todo(
                parcel.readLong(),
                parcel.readString(),
                parcel.readString(),
                parcel.readSerializable() as Date)

        override fun Todo.write(parcel: Parcel, flags: Int) {
            parcel.writeLong(id!!)
            parcel.writeString(title)
            parcel.writeString(description)
            parcel.writeSerializable(date)
        }
    }

}