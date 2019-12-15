package visapps.mystankin.data.news.util

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.text.SimpleDateFormat
import java.util.*

class NewsDateAdapter: TypeAdapter<Date>() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)

    override fun write(out: JsonWriter, value: Date) {
        throw NotImplementedError()
    }

    override fun read(`in`: JsonReader): Date = dateFormat.parse(`in`.nextString()) ?: Date()
}