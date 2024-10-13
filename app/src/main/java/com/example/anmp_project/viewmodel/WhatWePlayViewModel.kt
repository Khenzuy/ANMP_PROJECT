package com.example.anmp_project.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmp_project.model.Competition
import com.example.anmp_project.model.EsportsData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WhatWePlayViewModel(application: Application) : AndroidViewModel(application) {
    val competitionsLD = MutableLiveData<ArrayList<Competition>>()
    val competitionLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var queue: RequestQueue? = null
    private val TAG = "competitionVolleyTag"

    fun refresh() {
        loadingLD.value = true
        competitionLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())

        val url = "https://www.jsonkeeper.com/b/59UF"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                loadingLD.value = false
                Log.d(TAG, response)

                val competitionType = object : TypeToken<EsportsData>() {}.type
                val result = Gson().fromJson<EsportsData>(response, competitionType)

                competitionsLD.value = result.competitions
                Log.d(TAG, result.competitions.toString())
            },
            { error ->
                Log.d(TAG, error.toString())
                competitionLoadErrorLD.value = true
                loadingLD.value = false
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}
