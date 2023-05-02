package bogdan.markov.tasksheapbot.taskentity

import bogdan.markov.tasksheapbot.Constants
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.date
import kotlin.math.max
import kotlin.math.min

object TasksTable : LongIdTable() {
    // there's no composite key support, so use a dummy id instead and use chatId and messageId as regular fields.
    // see https://github.com/JetBrains/Exposed/issues/964 and https://github.com/JetBrains/Exposed/issues/353
    // todo chatId index
    val chatId = long(name = "chat_id")
//    val messageId = long(name = "message_id")
    val description = varchar(name = "description", length = Constants.TG_MESSAGE_MAX_LENGTH)
    val formattedDescription = text(name = "formattedDescription")
    val importance = uinteger(name = "importance").default(0u)
    val deadline = date("deadline").nullable()
}

class TaskEntity(messageId: EntityID<Long>) : LongEntity(messageId) {
    companion object : LongEntityClass<TaskEntity>(TasksTable)
    var chatId by TasksTable.chatId
//    var messageId by TasksTable.messageId
    var description by TasksTable.description
    var formattedDescription by TasksTable.formattedDescription
    var importance by TasksTable.importance
    var deadline by TasksTable.deadline

    val descriptionPreview: String
        get() = description.substring(0, min(Constants.TASK_DESCRIPTION_PREVIEW_LENGTH, description.length))

    // todo: formattedDescriptionPreview
}
