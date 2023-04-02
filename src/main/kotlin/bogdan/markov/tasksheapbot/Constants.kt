package bogdan.markov.tasksheapbot

object Constants {
    // Telegram constants, see https://limits.tginfo.me/en
    // `textLength.last` can be used instead (import dev.inmo.tgbotapi.types.textLength)
    const val TG_MESSAGE_MAX_LENGTH = 4096

    // My own constants
    const val TASK_DESCRIPTION_PREVIEW_LENGTH = 500
}
