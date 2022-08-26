package com.mawindavic.roomwalkthrough

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.mawindavic.roomwalkthrough.adapter.UserAdapter
import com.mawindavic.roomwalkthrough.data.RoomRepository
import com.mawindavic.roomwalkthrough.data.RoomWalkDB
import com.mawindavic.roomwalkthrough.data.User
import com.mawindavic.roomwalkthrough.data.UserAction
import com.mawindavic.roomwalkthrough.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {


    private val mainViewModel: MainViewModel by viewModels {
        val db: RoomWalkDB = RoomWalkDB.instance(this)
        val repository = RoomRepository(db = db)
        MainViewModelFactory(this.application, repository = repository)
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = UserAdapter { user, action ->
            when (action) {
                UserAction.EDIT -> {
                    Timber.i("USER TO BE EDITED")
                    user.secondName = "User 1"
                    mainViewModel.update(user)
                }

                UserAction.REMOVE -> {
                    Timber.i("USER TO BE DELETED: ${user}")
                    mainViewModel.delete(user)
                }
            }

        }




        binding.recyclerView.adapter = adapter

        mainViewModel.allUsers.observe(this) {
            it?.let { users ->
                adapter.submitList(users)
            }

        }

        binding.floatingBtn.setOnClickListener { adduser() }

    }


    private fun adduser() {
        //Adding users
        val user = User(firstName = "Victor", secondName = "Mawinda")
        mainViewModel.add(user)
    }
}