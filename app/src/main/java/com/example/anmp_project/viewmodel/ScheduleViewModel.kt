//package com.example.anmp_project.viewmodel
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.anmp_project.model.Schedule
//import com.example.anmp_project.model.ScheduleDatabase
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class ScheduleViewModel(private val database: ScheduleDatabase) : ViewModel() {
//
//    val schedules: LiveData<List<Schedule>> = database.scheduleDao().getAllSchedules()
//
//    fun fetchSchedules(context: Context) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val jsonString = ScheduleDatabase.loadJsonFromAsset("data.json", context)
//            val gson = Gson()
//            val typeToken = object : TypeToken<List<Schedule>>() {}.type
//            val schedulesList: List<Schedule> = gson.fromJson(jsonString, typeToken)
//
//            database.scheduleDao().insertAll(schedulesList)
//        }
//    }
//}
