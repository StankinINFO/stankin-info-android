package visapps.mystankin.domain.model

class Mark(val title: String, val num: String, val factor: Double, val value: Int) {

    enum class MarkType(val num: String) {
        MODULE_FIRST("М1"),
        MODULE_SECOND("М2"),
        COURSE("К"),
        OFFSET("З"),
        EXAM("Э")
    }
}