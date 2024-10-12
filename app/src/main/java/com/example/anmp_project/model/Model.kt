package com.example.anmp_project.model

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

//data class Schedule(
//    val date: EventDate,
//    val event_name: String,
//    val esport_team: String,
//    val event_photo: String,
//    val event_time: String,
//    val venue: String,
//    val event_description: String
//)

data class EventDate(
    val day: Int,
    val month: String,
    val year: Int
)

data class EsportsData(
    val competitions: ArrayList<Competition>,
//   val schedule: ArrayList<Schedule>
)
