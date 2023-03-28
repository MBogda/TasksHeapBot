package bogdan.markov.tasksheapbot

import dev.inmo.tgbotapi.types.chat.PrivateChat

object Util {
    val PrivateChat.fullName: String
        get() = "${this.firstName} ${this.lastName}"
}
