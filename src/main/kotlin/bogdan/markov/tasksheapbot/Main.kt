package bogdan.markov.tasksheapbot

import bogdan.markov.tasksheapbot.taskentity.TasksTable
import bogdan.markov.tasksheapbot.tasksheapbot.TasksHeapBot
import dev.inmo.tgbotapi.extensions.api.telegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

val CONFIG = loadConfig("config.yaml")

suspend fun main() {
    databaseInit()
    startBot()
}

private fun databaseInit() {
    Database.connect(
        "jdbc:h2:file:${CONFIG.database.path}",
        driver = "org.h2.Driver",
        user = CONFIG.database.user,
        password = CONFIG.database.password
    )
    transaction {
        SchemaUtils.create(TasksTable)
    }
}

private suspend fun startBot() {
    val bot = telegramBot(CONFIG.application.botApiToken)
    val tasksHeapBot = TasksHeapBot()

    bot.buildBehaviourWithLongPolling(defaultExceptionsHandler = {
        it.printStackTrace()
    }) {
        tasksHeapBot.runBot(this)
    }.join()
}
