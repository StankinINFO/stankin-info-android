package visapps.mystankin.domain.model

class User(val student: String, val surname: String, val initials: String, val stgroup: String) {

    val isEmpty get() = student == ""

    companion object {
        val EMPTY = User("", "", "", "")
    }
}