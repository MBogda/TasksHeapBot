import dev.inmo.tgbotapi.extensions.api.telegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import tasksheapbot.TasksHeapBot

val CONFIG = loadConfig("config.yaml")

suspend fun main() {
    val bot = telegramBot(CONFIG.application.botApiToken)
    val tasksHeapBot = TasksHeapBot()

    bot.buildBehaviourWithLongPolling {
        tasksHeapBot.runBot(this)
    }.join()
}
