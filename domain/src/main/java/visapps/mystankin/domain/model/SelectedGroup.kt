package visapps.mystankin.domain.model

class SelectedGroup (val id: Int = -1, val group: String = "", val subClass: Int = 0) {

    val isEmpty get() = id == -1

    val subClassTitle get() = if(subClass == 0) { "A" } else { "Б" }

    companion object {
        val EMPTY = SelectedGroup(-1)
    }

}