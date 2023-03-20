package tasksheapbot

import dev.inmo.tgbotapi.extensions.api.bot.getMe
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import util.fullName

class TasksHeapBot {

    suspend fun runBot(context: BehaviourContext) {
        return context.main()
    }

    private suspend fun BehaviourContext.main() {
        println(getMe())

        onCommand("start") {
            startMessage(it)
        }
    }

    private suspend fun BehaviourContext.startMessage(inputMessage: CommonMessage<TextContent>) {
        inputMessage.chat.ifPrivateChat { privateChat ->
            privateChat.id
            reply(
                to = inputMessage,
                text = "Hello ${privateChat.fullName}! Welcome to TasksHeap. <bla-bla-bla>."
            )
        } ?: reply(
            to = inputMessage,
            text = "Sorry, I can work only in private chats."
        )
    }
}
