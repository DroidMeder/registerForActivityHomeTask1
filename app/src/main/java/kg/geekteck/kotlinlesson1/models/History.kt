package kg.geekteck.kotlinlesson1.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(
    @PrimaryKey
    var createdAt: Long,
    val content: String?)