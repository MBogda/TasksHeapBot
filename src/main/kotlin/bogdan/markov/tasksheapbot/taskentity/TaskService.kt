package bogdan.markov.tasksheapbot.taskentity

import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import org.jetbrains.exposed.sql.transactions.transaction

object TaskService {
    fun createTask(chatId: Long, text: String, textSources: TextSourcesList): TaskEntity {
        return transaction {
            val newTask = TaskEntity.new {
                this.chatId = chatId
                this.description = text
                this.formattedDescription = text    // todo: textSources converted; use yaml-formatter
            }

            return@transaction newTask
        }
    }
}
