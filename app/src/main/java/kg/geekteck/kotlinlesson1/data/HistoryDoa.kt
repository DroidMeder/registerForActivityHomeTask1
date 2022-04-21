package kg.geekteck.kotlinlesson1.data

import androidx.room.*
import kg.geekteck.kotlinlesson1.models.History

@Dao
interface HistoryDoa {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(history: History)

    @Query("SELECT * FROM history")
    fun getAllHistory(): List<History>

    @Query("SELECT * FROM history ORDER BY createdAt DESC")
    fun getSortedHistory(): List<History>

    @Delete
    fun deleteAll(histories : List<History>)
}