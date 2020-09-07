package com.kalashnyk.denys.movaapp.ui.navigator.model

import android.os.Parcel
import android.os.Parcelable

class PageNavigationItem(
    val destination: Pages,
    var payload: PayloadObject = Payload()) : Parcelable {

    constructor(parcel: Parcel) : this(
        Pages.valueOf(parcel.readString()?.toUpperCase().toString()),
        parcel.readParcelable(PayloadObject::class.java.classLoader)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(this.destination.text)
        parcel.writeParcelable(payload, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PageNavigationItem> {
        override fun createFromParcel(parcel: Parcel): PageNavigationItem {
            return PageNavigationItem(parcel)
        }

        override fun newArray(size: Int): Array<PageNavigationItem?> {
            return arrayOfNulls(size)
        }
    }

}