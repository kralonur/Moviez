package com.example.moviez.paging

import androidx.paging.DataSource
import com.example.moviez.enums.PersonQueryType
import com.example.moviez.model.person.Person
import com.example.moviez.repositories.PersonRepository
import com.example.moviez.repositories.TrendingRepository
import kotlinx.coroutines.CoroutineScope

class PersonDataSourceFactory(
    private val personRepository: PersonRepository,
    private val trendingRepository: TrendingRepository,
    private val scope: CoroutineScope,
    private val queryType: PersonQueryType
) : DataSource.Factory<Int, Person>() {
    override fun create(): DataSource<Int, Person> {
        return PersonDataSource(
            personRepository, trendingRepository, scope, queryType
        )
    }
}