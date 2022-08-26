package com.mawindavic.roomwalkthrough

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mawindavic.roomwalkthrough.data.RoomRepository
import com.mawindavic.roomwalkthrough.data.User
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RoomRepository) : ViewModel() {
    val allUsers = repository.users

    fun add(vararg user: User) {
        viewModelScope.launch {
            repository.insert(*user)
        }
    }

    fun update(vararg user: User) {
        viewModelScope.launch {
            repository.update(*user)
        }
    }

    fun delete(vararg user: User) {
        viewModelScope.launch {
            repository.delete(user = user)
        }
    }
}


@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(application: Application, private val repository: RoomRepository) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository = repository) as T
    }

}