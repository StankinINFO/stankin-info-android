package visapps.mystankin.data.news.api

import visapps.mystankin.data.news.models.NewsEntity

class NewsResponse(val news: List<NewsEntity>, val count: Int)