package com.dicoding.finalfundamentalssubmission.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity (tableName = "favoritedUsers")
@Parcelize
data class FavoriteUserEntity(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "username")
    var username : String = "",

    @field:ColumnInfo(name = "avatar")
    var avatarUrl : String? = null,
): Parcelable