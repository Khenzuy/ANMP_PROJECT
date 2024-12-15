package com.example.anmp_project.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @ColumnInfo(name = "first_name")
    var firstName:String,
    @ColumnInfo(name = "last_name")
    var lastName:String,
    @ColumnInfo(name = "username")
    var username:String,

    @ColumnInfo(name = "password")
    var password:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

@Entity(tableName = "apply")
data class Apply(
    @ColumnInfo(name = "game")
    var game:String,
    @ColumnInfo(name = "team")
    var team:String,
    @ColumnInfo(name = "description")
    var description:String,
    @ColumnInfo(name = "status")
    var status: String = "WAITING"
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}


data class Competition(
    val id: Int,
    val game_photo: String,
    val game_name: String,
    val game_short_name: String,
    val game_description: String,
    val team_achievements: ArrayList<TeamAchievement>
)

data class TeamAchievement(
    val achievement: String,
    val team_name: String,
    val year: Int
)

data class Schedule(
    val date: EventDate,
    val event_name: String,
    val esport_team: String,
    val event_photo: String,
    val event_time: String,
    val venue: String,
    val event_description: String
)

data class EventDate(
    val day: Int,
    val month: String,
    val year: Int
)

data class EsportsData(
    val competitions: ArrayList<Competition>,
//   val schedule: ArrayList<Schedule>
)

data class TeamDetail(
    val player_role: String,
    val player_name: String,
    val player_image: String
)

data class TeamData(
    val team_name: String
)