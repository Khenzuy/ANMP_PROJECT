//package com.example.anmp_project.viewmodel
//
//import android.app.Application
//import android.util.Log
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.viewModelScope
//import com.example.anmp_project.model.Apply
//import com.example.anmp_project.model.ApplyDatabase
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.launch
//import kotlin.coroutines.CoroutineContext
//
//class ApplyTeamSubmitViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
//    private val job = Job()
//    private val applyDao = ApplyDatabase.buildDatabase(application).applyDao()
//    val insertionSuccessLD = MutableLiveData<Boolean>()
//
//    fun addApply(list: List<Apply>) {
//        //ignore exception
////        launch {
////            try {
////                val db = ApplyDatabase.buildDatabase(getApplication()) // Ensure the database is built
////                db.applyDao().insertAll(*list.toTypedArray()) // Insert data into the database
////                insertionSuccessLD.postValue(true)
////            } catch (e: Exception) {
////                Log.e("ApplyTeamSubmitViewModel", "Error inserting data: ${e.message}")
////                insertionSuccessLD.postValue(false)
////            }
////        }
//
//        fun addApply(list: List<Apply>) {
//            launch {
//                val db = ApplyDatabase.buildDatabase(getApplication())
//                db.applyDao().insertAll(*list.toTypedArray())
//            }
//        }
//    }
//
//    override val coroutineContext: CoroutineContext
//        get() = job + Dispatchers.IO
//}
//
