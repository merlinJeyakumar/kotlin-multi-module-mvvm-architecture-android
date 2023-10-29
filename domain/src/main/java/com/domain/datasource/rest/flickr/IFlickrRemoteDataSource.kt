package com.domain.datasource.rest.flickr

import androidx.paging.PagingData
import com.domain.model.entities.SampleEntity
import kotlinx.coroutines.flow.Flow

interface IFlickrRemoteDataSource {
    suspend fun getFlickrDataSync(page: Int): Map<String, Any>
    fun provideFlickrPagination(): Flow<PagingData<SampleEntity>>
}