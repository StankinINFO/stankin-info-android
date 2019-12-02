package visapps.mystankin.app.news.home

import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.usecase.NewsUseCase
import javax.inject.Inject

class NewsHostViewModel @Inject constructor(val NewsUseCase: NewsUseCase)
    : StankinViewModel() {
}