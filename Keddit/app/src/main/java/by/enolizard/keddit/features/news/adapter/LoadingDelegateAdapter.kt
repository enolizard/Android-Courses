package by.enolizard.keddit.features.news.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.enolizard.keddit.R
import by.enolizard.keddit.commons.adapter.ViewType
import by.enolizard.keddit.commons.adapter.ViewTypeDelegateAdapter
import by.enolizard.keddit.commons.extensions.inflate

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item_loading))
}
