package com.example.anmp_project.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: User)

    @Query("SELECT * FROM users ORDER BY username ASC")
    fun selectAllUsers(): List<User>

    @Query("UPDATE users SET first_name = :firstName, last_name = :lastName, " +
            "username = :username, password = :password WHERE id = :id")
    fun update(firstName: String, lastName: String, username: String, password: String, id: Int)

    @Update
    fun update(user: User)

    @Query("SELECT * FROM users WHERE id = :id")
    fun selectUser(id: Int): User

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    fun getUserByUsername(username: String): User?
}
