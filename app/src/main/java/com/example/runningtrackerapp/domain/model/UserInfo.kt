package com.example.runningtrackerapp.domain.model


data class UserInfo(
    val name: String = "",
    val weight: Float = 0f,
    val height: Float = 0f,
    var state: UserInfoState = UserInfoState.EmptyFields
)

enum class UserInfoState{
    Success,
    EmptyFields,
    WrongInfo
}