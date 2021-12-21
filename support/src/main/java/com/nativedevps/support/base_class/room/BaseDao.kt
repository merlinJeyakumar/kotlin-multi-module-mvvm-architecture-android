package com.nativedevps.support.base_class.room


import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

import java.lang.reflect.ParameterizedType

@Dao
abstract class BaseDao<T : BaseEntity> {

    protected val tableName: String
        get() {
            val clazz = (javaClass.superclass!!.genericSuperclass as ParameterizedType)
                .actualTypeArguments[0] as Class<*>
            return clazz.simpleName
        }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(obj: List<T>): LongArray

    @Update
    abstract fun update(obj: T)

    @Delete
    abstract fun delete(obj: T)

    fun find(key: String, value: String): T {
        val query = SimpleSQLiteQuery(
            "select * from $tableName where $key=?",
            arrayOf<Any>(value)
        )
        return doFind(query)
    }

    fun deleteAll() {
        val query = SimpleSQLiteQuery(
            "TRUNCATE $tableName"
        )
        doDeleteAll(query)
    }

    @RawQuery
    protected abstract fun doDeleteAll(query: SimpleSQLiteQuery): Int

    @RawQuery
    protected abstract fun doFind(query: SimpleSQLiteQuery): T


    fun findAllValid(): List<T> {
        val query = SimpleSQLiteQuery(
            "select * from $tableName where is_delete = 0 order by sortKey"
        )
        return doFindAllValid(query)
    }

    fun find(id: Long): T {
        val query = SimpleSQLiteQuery(
            "select * from $tableName where is_delete = 0 and id = ?",
            arrayOf<Any>(id)
        )
        return doFind(query)
    }

    @RawQuery
    protected abstract fun doFindAllValid(query: SupportSQLiteQuery): List<T>

    @RawQuery
    protected abstract fun doFind(query: SupportSQLiteQuery): T

}
