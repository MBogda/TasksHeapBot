package bogdan.markov.tasksheapbot.taskentity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object TasksTable : LongIdTable() {
    // there's no composite key support, so use a dummy id instead and use chatId and messageId as regular fields.
    // see https://github.com/JetBrains/Exposed/issues/964 and https://github.com/JetBrains/Exposed/issues/353
    // todo 2 indices + maybe common unique index
    val chatId = long(name = "chat_id")
    val messageId = long(name = "message_id")
    val text = varchar(name = "text", length = 4096)    // todo: constant
    // todo: think more about priority values, i.e. zeors, negatives and so on (and rename it as weight?)
    val priority = integer(name = "priority").default(0)
}

class TaskEntity(messageId: EntityID<Long>) : LongEntity(messageId) {
    companion object : LongEntityClass<TaskEntity>(TasksTable)
    var chatId by TasksTable.chatId
    var messageId by TasksTable.messageId
    var text by TasksTable.text
    var priority by TasksTable.priority
}
