package bogdan.markov.tasksheapbot

import com.charleskorn.kaml.Yaml
import dev.inmo.tgbotapi.types.message.textsources.*
import dev.inmo.tgbotapi.utils.buildEntities
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestSourcesListSerialization {

    @Test
    fun testYamlSerialization() {
        // textSources=[RegularTextSource(source=Гав ), StrikethroughTextSource(source=Хрю, subsources=[RegularTextSource(source=Хрю)]), RegularTextSource(source= ), BoldTextSource(source=Мяу, subsources=[RegularTextSource(source=Мяу)])]
        val textSources: TextSourcesList = buildEntities {
            add(regular("Гав "))
            add(strikethrough("Хрю"))
            add(regular(" "))
            add(bold("Мяу"))
        }

        println(textSources)

        val serializedTextSources = Yaml.default.encodeToString(ListSerializer(TextSourceSerializer), textSources)
        val deserializedTextSource = Yaml.default.decodeFromString(ListSerializer(TextSourceSerializer), serializedTextSources)
        assertEquals(textSources, deserializedTextSource)
    }

    @Test
    fun testYamlSerialization2() {
        // textSources=[RegularTextSource(source=Гав ), StrikethroughTextSource(source=Хрю, subsources=[RegularTextSource(source=Хрю)]), RegularTextSource(source= ), BoldTextSource(source=Мяу, subsources=[RegularTextSource(source=Мяу)])]
        val textSources: TextSourcesList = buildEntities {
            add(regular("Гав "))
            add(strikethrough("Хрю"))
            add(regular(" "))
            add(bold("Мяу"))
        }

        println(textSources)

        val serializedTextSources = Yaml.default.encodeToString(textSources)
        val deserializedTextSource: TextSourcesList = Yaml.default.decodeFromString(serializedTextSources)
        assertEquals(textSources, deserializedTextSource)
    }
}
