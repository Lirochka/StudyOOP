package com.example.studyoop
/**
 * методы должны быть паблик и овераид
 * свойства должны быть приватными
 * зависимости должны приходить в конструктор как интерфейсы
 */
interface Repository {

    suspend fun fetch(): Data

    class Base(
        private val cloudDataSource: DataSource,
        private val cacheDataSource: MutableDataSource
    ) : Repository {
        override suspend fun fetch(): Data {
            return if (cacheDataSource.contains())
                Data(cacheDataSource.fetch())
            else
                Data(cloudDataSource.fetch())
        }
    }
}

interface DataSource {
    suspend fun fetch(): String
}

interface MutableDataSource : DataSource {
    suspend fun contains(): Boolean
}

class Data(private val value: String)
