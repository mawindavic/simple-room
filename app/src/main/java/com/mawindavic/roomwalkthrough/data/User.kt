package com.mawindavic.roomwalkthrough.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Long = 0,
    @ColumnInfo(name = "user_first_name")
    var firstName: String,
    @ColumnInfo(name = "user_second_name")
    var secondName: String
)


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg user: User)

    @Delete
    fun delete(vararg user: User)

    @Query("SELECT * FROM user_table")
    fun liveUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user_table")
    fun allUsers(): List<User>
}
