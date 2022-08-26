package com.mawindavic.roomwalkthrough.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomRepository(private val db: RoomWalkDB) {
    private val userDao = db.userDao()

    val users = userDao.liveUsers()
    suspend fun insert(vararg user: User) {
        withContext(Dispatchers.IO) {
            userDao.insert(*user)
        }
    }
    suspend fun update(vararg user: User) {
        withContext(Dispatchers.IO) {
            userDao.update(*user)
        }
    }
    suspend fun delete(vararg user: User) {
        withContext(Dispatchers.IO) {
            userDao.delete(*user)
        }
    }
}
