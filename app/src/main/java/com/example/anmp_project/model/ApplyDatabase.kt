//package com.example.anmp_project.model
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.anmp_project.util.DB_NAME
//import com.example.anmp_project.util.MIGRATION_1_2
//
//@Database(entities = [Apply::class], version = 2)
//abstract class ApplyDatabase : RoomDatabase() {
//
//    abstract fun applyDao(): ApplyDao
//
//
//    companion object {
//        @Volatile
//        private var instance: ApplyDatabase? = null
//        private val LOCK = Any()
//
//        fun buildDatabase(context: Context) = Room.databaseBuilder(
//            context.applicationContext, ApplyDatabase::class.java, DB_NAME
//        )
//            .addMigrations(MIGRATION_1_2)
//            .fallbackToDestructiveMigrationFrom().build()
//
//        operator fun invoke(context: Context) {
//            if (instance == null) {
//                synchronized(LOCK) {
//                    instance ?: buildDatabase(context).also {
//                        instance = it
//                    }
//                }
//            }
//        }
//    }
//}
