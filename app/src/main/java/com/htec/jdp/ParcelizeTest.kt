package com.htec.jdp

import android.os.Parcel
import android.os.Parcelable

data class ParcelizeTest(var test1 : Int, var test2: Int) : Parcelable {
    constructor(source : Parcel) : this(source.readInt(), source.readInt())

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flag: Int) {
        dest?.writeInt(this.test1)
        dest?.writeInt(this.test2)
    }

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<ParcelizeTest> = object : Parcelable.Creator<ParcelizeTest> {
            override fun createFromParcel(parcel: Parcel): ParcelizeTest {
                return ParcelizeTest(parcel)
            }

            override fun newArray(size: Int): Array<ParcelizeTest?> {
               return arrayOfNulls(size)
            }
        }
    }
}