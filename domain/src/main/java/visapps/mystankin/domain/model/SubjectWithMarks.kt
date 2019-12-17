package visapps.mystankin.domain.model

class SubjectWithMarks(val subject: String,
                       val moduleFirst: Int?,
                       val moduleSecond: Int?,
                       val course: Int?,
                       val offset: Int?,
                       val exam: Int?,
                       val coefficient: Double?) {

    companion object Factory{
        fun fromMarks(marks: List<Mark>): SubjectWithMarks {
            val subject = marks.first().title
            val moduleFirst = marks.firstOrNull { it.num == Mark.MarkType.MODULE_FIRST.num }?.value
            val moduleSecond = marks.firstOrNull { it.num == Mark.MarkType.MODULE_SECOND.num }?.value
            val course = marks.firstOrNull { it.num == Mark.MarkType.COURSE.num }?.value
            val offset = marks.firstOrNull { it.num == Mark.MarkType.OFFSET.num }?.value
            val exam = marks.firstOrNull { it.num == Mark.MarkType.EXAM.num }?.value
            val coefficient = marks.minBy { it.factor }?.factor ?: 0
            return SubjectWithMarks(subject, moduleFirst, moduleSecond, course, offset, exam, 0.0)
        }
    }
}