package com.nativedevps.domain.entity.flickr

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.nativedevps.support.base_class.room.BaseEntity

/*
* [Please maintain table name, column format]
* TableName = "tbl_title"
* ColumnName = "title_etc"
*/
@Entity(tableName = SampleEntity.TABLE_NAME)
open class SampleEntity : BaseEntity() {

    companion object {
        const val TABLE_NAME = "tbl_sample"
    }

    object Fields {
        const val ID = "id"
        const val TITLE = "title"
        const val PAGE = "page"
    }

    @PrimaryKey
    @SerializedName(Fields.ID)
    @ColumnInfo(name = Fields.ID)
    var id: String = ""

    @SerializedName(Fields.TITLE)
    @ColumnInfo(name = Fields.TITLE)
    var title: String = ""

    @SerializedName(Fields.PAGE)
    @ColumnInfo(name = Fields.PAGE)
    var page: Int = 0
}