package by.enolizard.keddit.features.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.enolizard.keddit.R
import by.enolizard.keddit.commons.InfiniteScrollListener
import by.enolizard.keddit.commons.RedditNews
import by.enolizard.keddit.commons.RxBaseFragment
import by.enolizard.keddit.commons.extensions.inflate
import by.enolizard.keddit.features.news.adapter.NewsAdapter
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : RxBaseFragment() {

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    private val newsList: RecyclerView by lazy { news_list }
    private val newsManager by lazy { NewsManager() }
    private var redditNews: RedditNews? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.news_fragment, container, false)
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newsList.apply {
            val linearManager = LinearLayoutManager(context)
            layoutManager = linearManager
            setHasFixedSize(true)
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearManager))
        }

        initAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            (newsList.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (newsList.adapter as NewsAdapter).getNews()

        if (redditNews != null && news.size > 0) {
            outState.putParcelable(KEY_REDDIT_NEWS, redditNews!!.copy(news = news))
        }
    }

    private fun requestNews() {
        val subscription = newsManager.getNews(redditNews?.after ?: "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { retrievedNews ->
                    redditNews = retrievedNews
                    (newsList.adapter as NewsAdapter).addNews(retrievedNews.news)
                },
                { e ->
                    Snackbar.make(newsList, e.message ?: "", Snackbar.LENGTH_LONG).show()
                }
            )

        subscriptions.add(subscription)
    }

    private fun initAdapter() {
        if (newsList.adapter == null) {
            newsList.adapter = NewsAdapter()
        }
    }
}
