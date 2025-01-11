//package com.example.anmp_project.model
//
//import android.util.Log
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import androidx.room.Update
//
//@Dao
//interface ApplyDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(vararg apply: Apply)
//
//    @Query("SELECT * FROM apply")
//    fun selectAllApply(): List<Apply>
//
//    @Query("UPDATE apply SET game = :game, team = :team, " +
//            "description = :description WHERE id = :id")
//    fun update(game: String, team: String, description: String, id: Int)
//
//    @Update
//    fun update(apply: Apply) {
//        Log.d("ApplyDao", "Updating Apply: $apply")
//    }
//
//    @Query("SELECT * FROM apply WHERE id = :id")
//    fun selectApply(id: Int): Apply
//
//    @Delete
//    fun deleteApply(apply: Apply)
//}