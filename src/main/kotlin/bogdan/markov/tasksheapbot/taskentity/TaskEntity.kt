package bogdan.markov.tasksheapbot.taskentity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object TasksTable : LongIdTable(columnName = "chatId") {
    val text = varchar(name = "text", length = 4096)    // todo: constant
    val priority = integer(name = "priority").default(0)
}

class TaskEntity(chatId: EntityID<Long>) : LongEntity(chatId) {
    companion object : LongEntityClass<TaskEntity>(TasksTable)
    var text by TasksTable.text
    var priority by TasksTable.priority
}
