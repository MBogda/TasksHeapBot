import dev.inmo.tgbotapi.extensions.api.answers.answerCallbackQuery
import dev.inmo.tgbotapi.extensions.api.bot.getMe
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.telegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onContentMessage
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onDataCallbackQuery
import dev.inmo.tgbotapi.extensions.utils.types.buttons.dataButton
import dev.inmo.tgbotapi.extensions.utils.types.buttons.flatInlineKeyboard
import dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard
import dev.inmo.tgbotapi.types.message.textsources.regular
import dev.inmo.tgbotapi.utils.bold
import dev.inmo.tgbotapi.utils.row
import kotlinx.coroutines.delay

val CONFIG = loadConfig("config.yaml")

suspend fun main() {
    val bot = telegramBot(CONFIG.application.botApiToken)

    bot.buildBehaviourWithLongPolling {
        println(getMe())

        onContentMessage { message ->
            reply(
                message,
                replyMarkup = inlineKeyboard {
                    row {
                        dataButton("16.11.2022. Voli. 39.84 $", "d1")
                    }
                    row {
                        dataButton("14.11.2022. Mega. 34.00 $", "d2")
                    }
                },
            ) {
                +regular("Inline")
                bold("Keyboard")
            }
            reply(
                message,
                replyMarkup = flatInlineKeyboard {
                    dataButton("16.11.2022. Voli. 39.84 $", "d3")
                    dataButton("14.11.2022. Mega. 34.00 $", "d4")
                },
            ) {
                +regular("FlatInline")
                bold("Keyboard")
            }
        }

        // todo: Взять базу данных транзакций (хотя бы за месяц) и попробовать её выводить разными способами
        //  (пока что без ввода новой инфы). Желательно код всех способов сохранять для лёгкого переключения
        //  между отображениями (а-ля конфиг). Можно даже попробовать переключать способы прямо через самого же бота =)

        onDataCallbackQuery { callback ->
            delay(5000)
            when (callback.data) {
                "d1" -> answerCallbackQuery(
                    callback,
                    "You're awesome!"
                )
                "d2" -> answerCallbackQuery(
                    callback,
                    text = "Alert!!!",
                    showAlert = true,
                )
                "d3" -> answerCallbackQuery(
                    callback,
                    text = "Cached?",
                    cachedTimeSeconds = 3600,
                )
                "d4" -> answerCallbackQuery(
                    callback,
                    text = "Cached ALEEEEEERTTTT !!!!!11111ONEONE11ONE1",
                    showAlert = true,
                    cachedTimeSeconds = 3600,
                )
            }
        }
    }.join()
}
