package com.example.anmp_project.util

import android.content.Context
import com.example.anmp_project.model.EsportsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

val DB_NAME = "esportdb"

fun buildDb(context: Context): EsportsDatabase {
    return EsportsDatabase.getDatabase(context, CoroutineScope(Dispatchers.IO))
}
