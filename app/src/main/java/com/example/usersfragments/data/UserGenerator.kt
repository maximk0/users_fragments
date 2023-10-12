package com.example.usersfragments.data

import android.util.Log

object UserGenerator {

    var userList = listOf<User>()
        private set

    init {
        userList = userList()
    }

    private fun userList(): List<User> {
        val contacts = mutableListOf<User>()
        for (i in 1..100) {
            contacts.add(User(i, "Имя$i", "Фамилия$i", "8-800-$i$i"))
        }
        return contacts
    }

    fun getUser(userId: Int) = userList.find { it.id == userId }

    fun editUserName(userId: Int, userName: String) {
        val user = getUser(userId)
        user?.let { user ->
            user.firstName = userName
        }
    }

    fun editUserLastName(userId: Int, lastName: String) {
        val user = getUser(userId)
        user?.let { user ->
            user.lastName = lastName
        }
    }

    fun editUserNumber(userId: Int, number: String) {
        val user = getUser(userId)
        user?.let { user ->
            user.number = number
        }
        Log.d("TAG", "USER_DETAIL edit: $user")
    }
}