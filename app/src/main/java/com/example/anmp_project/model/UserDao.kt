package com.example.anmp_project.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Query("SELECT * FROM users WHERE id = :id")
    fun selectUser(id: Int): User?

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsernameLive(username: String): LiveData<User>

    @Query("UPDATE users SET like_count = like_count + 1 WHERE username = :username")
    fun incrementLikeCount(username: String)
}

