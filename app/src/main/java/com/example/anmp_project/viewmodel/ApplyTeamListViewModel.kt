package com.example.anmp_project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.anmp_project.model.Apply
import com.example.anmp_project.model.ApplyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ApplyTeamListViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    val applyLD = MutableLiveData<List<Apply>>()
    val applyLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        applyLoadErrorLD.value = false
        launch {
            //ignore exception
//            try {
//                val db = ApplyDatabase.buildDatabase(getApplication()) // Build the database
//                val applyList = db.applyDao().selectAllApply() // Query the Apply table
//                applyLD.postValue(applyList)
//            } catch (e: Exception) {
//                applyLoadErrorLD.postValue(true)
//            } finally {
//                loadingLD.postValue(false)
//            }
            val db = ApplyDatabase.buildDatabase(
                getApplication()
            )

            applyLD.postValue(db.applyDao().selectAllApply())
            loadingLD.postValue(false)
        }
    }
    fun clearTask(apply: Apply) {
        launch {
            val db = ApplyDatabase.buildDatabase(
                getApplication()
            )
            db.applyDao().deleteApply(apply)

            applyLD.postValue(db.applyDao().selectAllApply())
        }
    }




}