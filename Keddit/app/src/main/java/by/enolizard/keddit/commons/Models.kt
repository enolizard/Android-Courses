package by.enolizard.keddit.commons

import android.os.Parcel
import android.os.Parcelable
import by.enolizard.keddit.commons.adapter.AdapterConstants
import by.enolizard.keddit.commons.adapter.ViewType
import by.enolizard.keddit.commons.extensions.createParcel

data class RedditNewsItem(
    val author: String,
    val title: String,
    val numComments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String
) : ViewType, Parcelable {

    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { RedditNewsItem(it) }
    }

    constructor(parcelIn: Parcel) : this(
        parcelIn.readString()!!,
        parcelIn.readString()!!,
        parcelIn.readInt(),
        parcelIn.readLong(),
        parcelIn.readString()!!,
        parcelIn.readString()!!
    )

    override fun getViewType() = AdapterConstants.NEWS

    override fun writeToParcel(dest: Parcel, p1: Int) {
        dest.writeString(author)
        dest.writeString(title)
        dest.writeInt(numComments)
        dest.writeLong(created)
        dest.writeString(thumbnail)
        dest.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }
}

data class RedditNews(
    val after: String,
    val before: String,
    val news: List<RedditNewsItem>
) : Parcelable {

    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { RedditNews(it) }
    }

    constructor(parcelIn: Parcel) : this(
        parcelIn.readString()!!,
        parcelIn.readString()!!,
        mutableListOf<RedditNewsItem>().apply {
            parcelIn.readTypedList(this, RedditNewsItem.CREATOR)
        }
    )

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(after)
        p0.writeString(before)
        p0.writeTypedList(news)
    }

    override fun describeContents(): Int {
        return 0
    }
}
