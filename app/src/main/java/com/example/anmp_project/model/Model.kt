package com.example.anmp_project.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

// Entity untuk User
@Entity(tableName = "users")
data class User(
    var firstName: String,
    var lastName: String,
    var username: String,
    var password: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

// Entity untuk Competition
@Entity(tableName = "competitions")
data class Competition(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "game_photo") val game_photo: String,
    @ColumnInfo(name = "game_name") val game_name: String,
    @ColumnInfo(name = "game_short_name") val game_short_name: String,
    @ColumnInfo(name = "game_description") val game_description: String
)

// Entity untuk TeamAchievement
@Entity(
    tableName = "team_achievements",
    foreignKeys = [
        ForeignKey(
            entity = Competition::class,
            parentColumns = ["id"],
            childColumns = ["competition_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TeamAchievement(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "achievement") val achievement: String,
    @ColumnInfo(name = "team_name") val team_name: String,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "competition_id") val competition_id: Int // Foreign key to Competition
)

// Data class untuk Competition beserta Achievement-nya (One-to-Many Relation)
data class CompetitionWithAchievements(
    @Embedded val competition: Competition,
    @Relation(
        parentColumn = "id",
        entityColumn = "competition_id"
    )
    val achievements: List<TeamAchievement>
)

// Data class untuk EsportsData
data class EsportsData(
    val competitions: List<Competition>
)
