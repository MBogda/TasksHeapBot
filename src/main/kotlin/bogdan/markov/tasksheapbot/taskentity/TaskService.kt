package bogdan.markov.tasksheapbot.taskentity

import org.jetbrains.exposed.sql.transactions.transaction

object TaskService {
    fun createTask(chatId: Long, text: String): TaskEntity {
        return transaction {
            val newTask = TaskEntity.new {
                this.chatId = chatId
                this.description = text
            }

            return@transaction newTask
        }
    }
}
