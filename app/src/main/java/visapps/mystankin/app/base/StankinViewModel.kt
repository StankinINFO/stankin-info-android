package visapps.mystankin.app.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class StankinViewModel: ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}