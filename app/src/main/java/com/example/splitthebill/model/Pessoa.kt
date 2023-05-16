package com.example.splitthebill.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pessoa(
    val id: Int,
    var name: String,
    var desc: String,
    var value: Double
    ): Parcelable