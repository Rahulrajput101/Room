package com.example.android.room.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.room.database.UserData
import com.example.android.room.database.UserDatabase
import com.example.android.room.repository.UserRepositary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class viewMOdel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<UserData>>

    private val repositary : UserRepositary

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repositary = UserRepositary(userDao)
        readAllData = repositary.readAllData

    }
    fun addUser(userData: UserData){
        viewModelScope.launch ( Dispatchers.IO ){
            repositary.addUser(userData)
        }
    }

    fun updateUser(userData: UserData){
        viewModelScope.launch (Dispatchers.IO ) {
            repositary.updateUser(userData)
        }
    }

    fun deleteUser(userData: UserData){
        viewModelScope.launch ( Dispatchers.IO) {
            repositary.deleteUser(userData)
        }
    }
    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO){
            repositary.deleteAllUser()
        }
    }




}