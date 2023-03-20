package util

import dev.inmo.tgbotapi.types.chat.PrivateChat

val PrivateChat.fullName: String
    get() = "${this.firstName} ${this.lastName}"
