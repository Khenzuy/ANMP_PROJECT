package com.example.anmp_project.model

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "competitions")
data class Competition(
    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "game_photo")
    val gamePhoto: String,

    @ColumnInfo(name = "game_name")
    val gameName: String,

    @ColumnInfo(name = "game_short_name")
    val gameShortName: String,

    @ColumnInfo(name = "game_description")
    val gameDescription: String
)

@Entity(tableName = "achievements")
data class Achievement(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "achievement")
    val achievement: String,

    @ColumnInfo(name = "team_name")
    val teamName: String,

    @ColumnInfo(name = "year")
    val year: Int,

    @ColumnInfo(name = "competition_id")
    val competitionId: Int
)
//kombinasi teamName,competition Id harus unique
@Entity(tableName = "teams", indices = [Index(value = ["teamName", "competitionId"], unique = true)])
data class Team(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val teamName: String,
    val competitionId: Int
)

@Entity(tableName = "members", indices = [Index(value = ["teamName", "memberName"], unique = true)])
data class Member(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "teamName")
    val teamName: String,

    @ColumnInfo(name = "memberName")
    val memberName: String,

    @ColumnInfo(name = "memberRole")
    val memberRole: String,

    @ColumnInfo(name = "memberImage")
    val memberImage: String
)

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "first_name")
    var firstName:String,

    @ColumnInfo(name = "last_name")
    var lastName:String,

    @ColumnInfo(name = "username")
    var username:String,

    @ColumnInfo(name = "password")
    var password:String,

    @ColumnInfo(name = "profile_image")
    var profileImage: String,

    @ColumnInfo(name = "team_description")
    var teamDescription: String,

    @ColumnInfo(name = "like_count")
    var likeCount: Int = 0
)


@Entity(tableName = "proposal")
data class Proposal(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "game")
    var game: String,

    @ColumnInfo(name = "team")
    var team: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "status")
    var status: String = "WAITING",

    @ColumnInfo(name = "username")
    var username: String
)


data class TeamAchievement(
    @SerializedName("achievement")
    val achievement: String,

    @SerializedName("team_name")
    val teamName: String,

    @SerializedName("year")
    val year: Int
)
data class CompetitionsWrapper(
    val competitions: List<CompetitionJson>
)

data class CompetitionJson(
    @SerializedName("id")
    val id: Int,

    @SerializedName("game_photo")
    val gamePhoto: String,

    @SerializedName("game_name")
    val gameName: String,

    @SerializedName("game_short_name")
    val gameShortName: String,

    @SerializedName("game_description")
    val gameDescription: String,

    @SerializedName("team_achievements")
    val teamAchievements: List<TeamAchievement>
)

data class Schedule(
    @Embedded
    val date: EventDate,

    @ColumnInfo(name = "event_name")
    val eventName: String,

    @ColumnInfo(name = "esport_team")
    val esportTeam: String,

    @ColumnInfo(name = "event_photo")
    val eventPhoto: String,

    @ColumnInfo(name = "event_time")
    val eventTime: String,

    @ColumnInfo(name = "venue")
    val venue: String,

    @ColumnInfo(name = "event_description")
    val eventDescription: String
)

data class EventDate(
    @ColumnInfo(name = "day")
    val day: Int,

    @ColumnInfo(name = "month")
    val month: String,

    @ColumnInfo(name = "year")
    val year: Int
)