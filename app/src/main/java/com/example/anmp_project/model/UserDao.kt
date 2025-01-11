package com.example.anmp_project.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Query("SELECT * FROM users ORDER BY username ASC")
    fun selectAllUsers(): List<User>

    @Query("UPDATE users SET first_name = :firstName, last_name = :lastName, username = :username, password = :password WHERE id = :id")
    fun update(id: Int, firstName: String, lastName: String, username: String, password: String): Int

    @Query("SELECT * FROM users WHERE id = :id")
    fun selectUser(id: Int): User?

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsernameLive(username: String): LiveData<User>

    @Query("UPDATE users SET like_count = like_count + 1 WHERE username = :username")
    fun incrementLikeCount(username: String)

}

