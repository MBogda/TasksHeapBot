package bogdan.markov.tasksheapbot

import com.charleskorn.kaml.Yaml
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import dev.inmo.tgbotapi.types.message.textsources.*
import dev.inmo.tgbotapi.utils.buildEntities
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import java.io.StringWriter
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
        println(serializedTextSources)

        val mySerialized = """
            - "regular"
              source: "Гав "
        """.trimIndent()

        val deserializedTextSource = Yaml.default.decodeFromString(ListSerializer(TextSourceSerializer), mySerialized)
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
        println(serializedTextSources)
        val deserializedTextSource: TextSourcesList = Yaml.default.decodeFromString(serializedTextSources)
        assertEquals(textSources, deserializedTextSource)
    }

    @Test
    fun testJsonSerialization() {
        // textSources=[RegularTextSource(source=Гав ), StrikethroughTextSource(source=Хрю, subsources=[RegularTextSource(source=Хрю)]), RegularTextSource(source= ), BoldTextSource(source=Мяу, subsources=[RegularTextSource(source=Мяу)])]
        val textSources: TextSourcesList = buildEntities {
            add(regular("Гав "))
            add(strikethrough("Хрю"))
            add(regular(" "))
            add(bold("Мяу"))
        }

        println(textSources)

        val serializedTextSources = Json.encodeToString(textSources)
        println(serializedTextSources)
        val deserializedTextSource: TextSourcesList = Json.decodeFromString(serializedTextSources)
        assertEquals(textSources, deserializedTextSource)
    }

    @Test
    fun testJsonSerialization2() {
        // textSources=[RegularTextSource(source=Гав ), StrikethroughTextSource(source=Хрю, subsources=[RegularTextSource(source=Хрю)]), RegularTextSource(source= ), BoldTextSource(source=Мяу, subsources=[RegularTextSource(source=Мяу)])]
        val textSources: TextSourcesList = buildEntities {
            add(regular("Гав "))
            add(strikethrough("Хрю"))
            add(regular(" "))
            add(bold("Мяу"))
        }

        println(textSources)

        val mapper = ObjectMapper(YAMLFactory())
        mapper.registerModule(KotlinModule.Builder().build())

        val stringWriter = StringWriter()
        mapper.writeValue(stringWriter, textSources)
        val serializedTextSources = stringWriter.toString()
        println(serializedTextSources)
        // здесь получился кривой аутпут: не сериализуются type и value.

        // с инпутом даже не стал заморачиваться.
//        mapper.readValue<TextSourcesList>(StringReader(serializedTextSources), TextSourcesList::class.java)
//        val deserializedTextSource: TextSourcesList = Json.decodeFromString(serializedTextSources)
//        assertEquals(textSources, deserializedTextSource)


    }
}
