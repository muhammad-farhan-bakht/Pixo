/**Designed and developed by 2021 Muhammad Farhan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/
package com.farhan.pixo.ui.gallery.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.farhan.pixo.arch.api.PixabayApi
import com.farhan.pixo.model.Images
import timber.log.Timber

private const val PIXABAY_STARTING_PAGE_INDEX = 1

class PixabayPagingSource(
        private val pixabayApi: PixabayApi,
        private val query: String
) : PagingSource<Int, Images>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Images> {
        val position = params.key ?: PIXABAY_STARTING_PAGE_INDEX

        return try {
            Timber.e("load Calls")
            Timber.e("position $position")
            Timber.e("params.loadSize ${params.loadSize}")
            val response = pixabayApi.getImages(query= query, page = position, perPage = params.loadSize)
            val images = response.imageList
            LoadResult.Page(
                    data = images,
                    prevKey = if (position == PIXABAY_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (images.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Images>): Int = PIXABAY_STARTING_PAGE_INDEX
}