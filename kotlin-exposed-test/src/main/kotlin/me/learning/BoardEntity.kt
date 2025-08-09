package me.learning

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BoardEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<BoardEntity>(Board)

    var title by Board.title
    var content by Board.content
    var author by Board.author

}