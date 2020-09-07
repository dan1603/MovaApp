package com.kalashnyk.denys.movaapp.ui.navigator.model

import android.os.Parcel
import android.os.Parcelable

class Payload(val id: String = "",
              private val pageNavigationItem: PageNavigationItem? = null,
              private val fromDeepLink: Boolean = false
) : PayloadObject {

    constructor(parcel: Parcel) : this(
        id = parcel.readString().toString(),
        pageNavigationItem = parcel.readParcelable(PageNavigationItem::class.java.classLoader),
        fromDeepLink = parcel.readByte() != 0.toByte()
    )

    fun isFromDeepLink(): Boolean {
        return fromDeepLink
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(pageNavigationItem, flags)
        parcel.writeByte(if (fromDeepLink) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Payload> {
        override fun createFromParcel(parcel: Parcel): Payload {
            return Payload(parcel)
        }

        override fun newArray(size: Int): Array<Payload?> {
            return arrayOfNulls(size)
        }
    }
}