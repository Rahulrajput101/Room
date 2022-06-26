package com.example.android.room.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
      fun addUser(userData: UserData)


    @Update
    fun updateUser(userData: UserData)

    @Delete
    fun deleteUser(userData : UserData)

     @Query("DELETE FROM user_table")
     fun deleteAllUser()

     @Query("SELECT * FROM user_table ORDER BY id ASC")
     fun readAllData(): LiveData<List<UserData>>




}