package by.enolizard.keddit.commons.models

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
