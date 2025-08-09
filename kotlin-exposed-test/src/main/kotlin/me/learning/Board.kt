package me.learning

object Board : BaseTable("boards") {
    val title = varchar("title", length = 256)
    val content = text("content")
    val author = reference("author_id", User.id)
}