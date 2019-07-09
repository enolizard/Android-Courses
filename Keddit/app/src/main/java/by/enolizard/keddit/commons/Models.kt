package by.enolizard.keddit.commons

import by.enolizard.keddit.commons.adapter.AdapterConstants
import by.enolizard.keddit.commons.adapter.ViewType

data class RedditNewsItem(
    val author: String,
    val title: String,
    val numComments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String
) : ViewType {
    override fun getViewType() = AdapterConstants.NEWS
}

data class RedditNews(
    val after: String,
    val before: String,
    val news: List<RedditNewsItem>
)