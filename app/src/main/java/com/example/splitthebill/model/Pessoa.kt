package com.example.splitthebill.model

import android.os.Parcelable
import android.text.Editable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pessoa(
    val id: Int,
    var name: String,
    var desc: String,
    var value: Editable
    ): Parcelable