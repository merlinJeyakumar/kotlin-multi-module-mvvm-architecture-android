package com.data.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.domain.model.entities.SampleEntity
import com.nativedevps.support.base_class.room.BaseDao

@Dao
abstract class SampleItemDao : BaseDao<SampleEntity>() {

    override val tableName: String
        get() = SampleEntity.TABLE_NAME

    @get:Query("SELECT * FROM " + SampleEntity.TABLE_NAME)
    abstract val getLiveItemList: LiveData<List<SampleEntity>>

    @get:Query("SELECT COUNT(id) FROM " + SampleEntity.TABLE_NAME)
    abstract val getCountOfItems: Long

    @Query("SELECT * FROM tbl_sample")
    abstract fun getPaging(): PagingSource<Int, SampleEntity>

    @Query("DELETE FROM tbl_sample")
    abstract fun clearAll()
}
