package bogdan.markov.tasksheapbot.taskentity

import com.charleskorn.kaml.Yaml
import dev.inmo.tgbotapi.types.message.textsources.TextSourceSerializer
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.decodeFromString
import org.jetbrains.exposed.sql.transactions.transaction

object TaskService {
    fun createTask(chatId: Long, text: String, textSources: TextSourcesList): TaskEntity {
        return transaction {
            val serializedTextSources = Yaml.default.encodeToString(ListSerializer(TextSourceSerializer), textSources)
            val newTask = TaskEntity.new {
                this.chatId = chatId
                this.description = text
                this.formattedDescription = serializedTextSources
            }

            return@transaction newTask
        }
    }

    val TaskEntity.textSources: TextSourcesList
//        get() = Yaml.default.decodeFromString(ListSerializer(TextSourceSerializer), this.formattedDescription)
        get() = Yaml.default.decodeFromString(string = this.formattedDescription)   // todo: can't read it; either fix or use json.
}
