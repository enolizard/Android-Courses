@file:JvmName("ExtensionsUtils")

package by.enolizard.keddit.commons.extensions

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import by.enolizard.keddit.R
import com.squareup.picasso.Picasso

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        this.setImageResource(R.mipmap.ic_launcher)
//        Picasso.get().load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.get().load(imageUrl).resize(40, 40).centerCrop().into(this)
    }
}

inline fun <reified T : Parcelable> createParcel(
    crossinline createFromParcel: (Parcel) -> T?
): Parcelable.Creator<T> =
    object : Parcelable.Creator<T> {
        override fun createFromParcel(p0: Parcel?): T? {
            return createFromParcel(p0)
        }

        override fun newArray(p0: Int): Array<out T?> {
            return arrayOfNulls(p0)
        }
    }