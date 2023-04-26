package com.example.mytestapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataCustomAnime(
 var image: String,
 var title: String,
 var detail:String,
): Parcelable
