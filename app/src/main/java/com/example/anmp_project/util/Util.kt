package com.example.anmp_project.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import android.content.Context
import com.example.anmp_project.model.EsportsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

val DB_NAME = "esportdb"

fun buildDb(context: Context): EsportsDatabase {
    return EsportsDatabase.getDatabase(context, CoroutineScope(Dispatchers.IO))
}

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    Picasso.get()
        .load(url)
        .placeholder(android.R.drawable.ic_menu_gallery)
        .error(android.R.drawable.ic_menu_close_clear_cancel)
        .into(this)
}
