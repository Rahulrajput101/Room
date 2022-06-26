package com.example.android.room.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.android.room.database.UserDao
import com.example.android.room.database.UserData

class UserRepositary( private val userDao: UserDao) {

    val readAllData : LiveData<List<UserData>> = userDao.readAllData()

    fun addUser(userData: UserData){
        userDao.addUser(userData)

    }
    fun updateUser(userData: UserData){
        userDao.updateUser(userData)
    }

    fun deleteUser(userData: UserData){
        userDao.deleteUser(userData)
    }

    fun deleteAllUser(){
        userDao.deleteAllUser()
    }
}