package kg.geekteck.kotlinlesson1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.geekteck.kotlinlesson1.models.History

@Database(entities = [History::class], version = 3)
abstract class AppDatabase : RoomDatabase(){
    abstract fun historyDao() : HistoryDoa
}
