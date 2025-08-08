package me.learning

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(User)

    var name by User.name
    var email by User.email
    var birthDay by User.birthDay
    var gender by User.gender

    fun changeName(newName: String) {
        name = newName
    }

    override fun toString(): String {
        return "UserEntity(id=$id, name='$name', email='$email', birthDay=$birthDay)"
    }
}