package com.example.anmp_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anmp_project.model.EventDate
import com.example.anmp_project.model.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class ScheduleViewModel : ViewModel() {

    val schedules = MutableLiveData<List<Schedule>>()
    val errorMessage = MutableLiveData<String>()

    fun fetchSchedules() {
        viewModelScope.launch {
            val fetchedSchedules = getSchedulesFromWebService()
            schedules.value = fetchedSchedules
        }
    }
    private suspend fun getSchedulesFromWebService(): List<Schedule> {
        return withContext(Dispatchers.IO) {
            try {
                val url = "https://www.jsonkeeper.com/b/902I"
                val response = URL(url).readText()
                val scheduleList = mutableListOf<Schedule>()
                val jsonObject = JSONObject(response)

                val competitionsArray = jsonObject.getJSONArray("schedule")
                for (i in 0 until competitionsArray.length()) {
                    val scheduleObj = competitionsArray.getJSONObject(i)
                    val dateObj = scheduleObj.getJSONObject("date")
                    val schedule = Schedule(
                        date = EventDate(
                            day = dateObj.getInt("day"),
                            month = dateObj.getString("month"),
                            year = dateObj.getInt("year")
                        ),
                        event_name = scheduleObj.getString("event_name"),
                        esport_team = scheduleObj.getString("esport_team"),
                        event_photo = scheduleObj.getString("event_photo"),
                        event_time = scheduleObj.getString("event_time"),
                        venue = scheduleObj.getString("venue"),
                        event_description = scheduleObj.getString("event_description")
                    )
                    scheduleList.add(schedule)
                }
                scheduleList
            } catch (e: Exception) {
                errorMessage.value = e.message
                emptyList()
            }
        }
    }
}