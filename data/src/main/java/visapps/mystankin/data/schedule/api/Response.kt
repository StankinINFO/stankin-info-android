package visapps.mystankin.data.schedule.api

class Response<T>(val success: Boolean, val total: Int, val results: List<T>)