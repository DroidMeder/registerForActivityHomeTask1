package kg.geekteck.kotlinlesson1

import android.app.Application
import androidx.room.Room
import kg.geekteck.kotlinlesson1.data.AppDatabase

class App : Application() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "db"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}