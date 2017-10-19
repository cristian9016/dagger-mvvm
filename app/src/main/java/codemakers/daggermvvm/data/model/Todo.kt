package codemakers.daggermvvm.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by Cristian on 17/10/2017.
 */

@Entity
class Todo(@PrimaryKey var id: Long?,
           var title: String?,
           var description: String?,
           var date: Date){

    @Ignore
    constructor():this(null,null,null,Date())

    @Ignore
    constructor(title: String, description: String?) : this(null, title, description, Date())

}