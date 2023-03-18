import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule

data class Config(
    val application: ApplicationConfig
)

data class ApplicationConfig(
    val botApiToken: String
)

fun loadConfig(configFile: String): Config {
    // To use more powerful configs see https://github.com/sksamuel/hoplite
    val reader = {}.javaClass.getResourceAsStream(configFile)?.bufferedReader()

    val mapper = ObjectMapper(YAMLFactory())
    mapper.registerModule(KotlinModule.Builder().build())

    return reader.use {
        mapper.readValue(it, Config::class.java)
    }
}
