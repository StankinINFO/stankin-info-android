package visapps.mystankin.data.news.util

import com.google.gson.JsonParseException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class NewsDateAdapter: TypeAdapter<Date>() {

    private val datePatterns = arrayOf("yyyy-MM-dd HH:mm:ssZZZZZ", "yyyy-MM-dd HH:mm:ss.SSSSSSZZZZZ")

    override fun write(out: JsonWriter, value: Date) {
        throw NotImplementedError()
    }

    override fun read(`in`: JsonReader): Date {
        val dateString = `in`.nextString()
        for(pattern in datePatterns) {
            try{
                return SimpleDateFormat(pattern, Locale.ENGLISH).parse(dateString) ?: Date()
            }
            catch (e: ParseException){ }
        }
        throw JsonParseException("Unable to parse date: $dateString");
    }
}