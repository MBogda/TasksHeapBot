package bogdan.markov.tasksheapbot.taskentity

import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.transactions.transaction

object TaskService {
    fun createTask(chatId: Long, text: String, textSources: TextSourcesList): TaskEntity {
        return transaction {
            val serializedTextSources = Json.encodeToString(textSources)
            val newTask = TaskEntity.new {
                this.chatId = chatId
                this.description = text
                this.formattedDescription = serializedTextSources
            }

            return@transaction newTask
        }
    }

    val TaskEntity.textSources: TextSourcesList
        get() = Json.decodeFromString(this.formattedDescription)
}
