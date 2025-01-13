package com.example.anmp_project.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedules")
    fun getAllSchedules(): LiveData<List<Schedule>>

    @Query("SELECT * FROM schedules WHERE event_name = :eventName")
    fun getScheduleByEventName(eventName: String): List<Schedule>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(schedules: List<Schedule>)
}



