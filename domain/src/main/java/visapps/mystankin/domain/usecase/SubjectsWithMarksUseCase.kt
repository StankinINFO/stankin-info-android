package visapps.mystankin.domain.usecase

import io.reactivex.Observable
import visapps.mystankin.domain.model.Mark
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.model.Semester
import visapps.mystankin.domain.model.SubjectWithMarks
import visapps.mystankin.domain.repository.MJRepository
import javax.inject.Inject

class SubjectsWithMarksUseCase (private val mjRepository: MJRepository) {

    operator fun invoke(student:String,password:String,semester:String): Observable<List<Mark>> {
        // тут маппим из репозитория в окончательный вид,

        // возможно также делаем subscribeOn, observeOn, передавая сюда Scheduler

        return mjRepository.getMarks(student,password,semester)
    }

}