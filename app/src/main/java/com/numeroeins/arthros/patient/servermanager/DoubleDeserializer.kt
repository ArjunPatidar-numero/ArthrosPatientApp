package  com.numeroeins.arthros.patient.servermanager

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type


class DoubleDeserializer : JsonDeserializer<Double?> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Double? {
        try {
            return json.asDouble
        } catch (e: Exception) {
        }
        return null
    }
}
