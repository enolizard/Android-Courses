package by.enolizard.keddit.features.news

import by.enolizard.keddit.api.NewsApi
import by.enolizard.keddit.api.NewsRestApi
import by.enolizard.keddit.commons.RedditNews
import by.enolizard.keddit.commons.RedditNewsItem
import io.reactivex.Observable

class NewsManager(private val api: NewsApi = NewsRestApi()) {

    fun getNews(after: String, limit: String = "10"): Observable<RedditNews> {
        return Observable.create { subcriber ->
            val callResponse = api.getNews(after, limit)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val dataResponse = response.body()!!.data
                val news = dataResponse.children.map {
                    val item = it.data
                    RedditNewsItem(
                        item.author,
                        item.title,
                        item.num_comments,
                        item.created,
                        item.thumbnail,
                        item.url
                    )
                }

                val redditNews = RedditNews(
                    dataResponse.after?:"",
                    dataResponse.before?: "",
                    news
                )
                subcriber.onNext(redditNews)
                subcriber.onComplete()
            } else {
                subcriber.onError(Throwable(response.message()))
            }
        }
    }
}
