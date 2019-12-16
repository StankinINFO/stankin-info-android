package visapps.mystankin.data.mj.api

import visapps.mystankin.domain.model.User

data class SemestersResponse(val semesters: List<String>, val surname: String, val initials: String, val stgroup: String) {

    fun toUser(student: String) : User = User(student, surname, initials, stgroup)
}