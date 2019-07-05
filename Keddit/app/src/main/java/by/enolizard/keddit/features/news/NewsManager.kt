package by.enolizard.keddit.features.news

import by.enolizard.keddit.commons.models.RedditNewsItem
import io.reactivex.Observable

class NewsManager {

    fun getNews(): Observable<List<RedditNewsItem>> {
        return Observable.create { subcriber ->
            val news = mutableListOf<RedditNewsItem>()

            for (i in 1..10) {
                news.add(
                    RedditNewsItem(
                        "author$i",
                        "Title $i",
                        i,
                        1457207701L - i * 200,
                        "https://pp.userapi.com/c626220/v626220754/302ff/Ah7bXmVh_No.jpg",
//                        "http://lorempixel.com/200/200/technics/$i",
                        "url"
                    )
                )
            }

            subcriber.onNext(news)
        }
    }
}
