package com.example.moviez.paging

import androidx.paging.DataSource
import com.example.moviez.enums.TVQueryType
import com.example.moviez.model.tv.TV
import com.example.moviez.repositories.TVRepository
import com.example.moviez.repositories.TrendingRepository
import kotlinx.coroutines.CoroutineScope

class TVDataSourceFactory(
    private val tvRepository: TVRepository,
    private val trendingRepository: TrendingRepository,
    private val scope: CoroutineScope,
    private val queryType: TVQueryType
) : DataSource.Factory<Int, TV>() {
    override fun create(): DataSource<Int, TV> {
        return TVDataSource(tvRepository, trendingRepository, scope, queryType)
    }
}