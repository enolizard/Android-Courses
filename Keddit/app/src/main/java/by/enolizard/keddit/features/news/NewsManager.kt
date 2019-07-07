package by.enolizard.keddit.features.news

import by.enolizard.keddit.api.RestApi
import by.enolizard.keddit.commons.models.RedditNewsItem
import io.reactivex.Observable

class NewsManager(private val api: RestApi = RestApi()) {

    fun getNews(limit: String = "10"): Observable<List<RedditNewsItem>> {
        return Observable.create { subcriber ->
            val callResponse = api.getNews("", limit)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val news = response.body()!!.data.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments, item.created, item.thumbnail, item.url)
                }
                subcriber.onNext(news)
                subcriber.onComplete()
            } else {
                subcriber.onError(Throwable(response.message()))
            }
        }
    }
}
