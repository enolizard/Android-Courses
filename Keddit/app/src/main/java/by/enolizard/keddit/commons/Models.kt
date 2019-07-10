package by.enolizard.keddit.commons

import android.os.Parcel
import android.os.Parcelable
import by.enolizard.keddit.commons.adapter.AdapterConstants
import by.enolizard.keddit.commons.adapter.ViewType

data class RedditNewsItem(
    val author: String,
    val title: String,
    val numComments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String
) : ViewType, Parcelable {

    override fun getViewType() = AdapterConstants.NEWS

    constructor(parcelIn: Parcel) : this(
        parcelIn.readString()!!,
        parcelIn.readString()!!,
        parcelIn.readInt(),
        parcelIn.readLong(),
        parcelIn.readString()!!,
        parcelIn.readString()!!
    )

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(author)
        p0.writeString(title)
        p0.writeInt(numComments)
        p0.writeLong(created)
        p0.writeString(thumbnail)
        p0.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RedditNewsItem> {
        override fun createFromParcel(parcel: Parcel): RedditNewsItem {
            return RedditNewsItem(parcel)
        }

        override fun newArray(size: Int): Array<RedditNewsItem?> {
            return arrayOfNulls(size)
        }
    }
}

data class RedditNews(
    val after: String,
    val before: String,
    val news: List<RedditNewsItem>
) : Parcelable {

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

    companion object CREATOR : Parcelable.Creator<RedditNews> {
        override fun createFromParcel(parcel: Parcel): RedditNews {
            return RedditNews(parcel)
        }

        override fun newArray(size: Int): Array<RedditNews?> {
            return arrayOfNulls(size)
        }
    }
}
