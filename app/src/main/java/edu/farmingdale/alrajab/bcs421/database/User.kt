package edu.farmingdale.alrajab.bcs421.database

import androidx.room.*

@Entity(tableName = "users")
data class User(

    @PrimaryKey (autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "first_name") var firstName: String?,
    @ColumnInfo(name = "last_name") var lastName: String?



){
@Ignore
        constructor(firstName: String?, lastName: String?) : this(null, firstName, lastName)
    }
