package com.example.usersfragments.data

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
}