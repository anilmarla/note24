package com.anil.notes24.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anil.notes24.model.User


@Dao
interface UserDao {
    //delete all users
    @Query("DELETE from users")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * from users WHERE isLoggedIn=1")
    fun isLoggedInUser(): LiveData<User>

   /* @Query("SELECT * from users")
    fun getAll()*/

    @Query("SELECT * FROM users")
    fun getUser(): LiveData<User>


}