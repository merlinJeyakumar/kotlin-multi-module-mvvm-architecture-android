package com.support.room


import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

import java.lang.reflect.ParameterizedType

@Dao
abstract class AppDao<T> {

    // tableName = StringUtil.toSnakeCase(clazz.getSimpleName());
    val tableName: String
        get() {
            val clazz = (javaClass.superclass!!.genericSuperclass as ParameterizedType)
                .actualTypeArguments[0] as Class<*>
            return clazz.simpleName
        }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(vararg objs: T): LongArray

    @Insert(onConflict = OnConflictStrategy.FAIL)
    abstract fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.FAIL)
    abstract fun insert(vararg objs: T): LongArray

    @Delete
    abstract fun delete(obj: T)

    fun deleteAll(): Int {
        val query = SimpleSQLiteQuery(
            "delete from $tableName"
        )
        return doDeleteAll(query)
    }

    fun findAllValid(): List<T> {
        val query = SimpleSQLiteQuery(
            "select * from $tableName where deleteFlag = 0 order by sortKey"
        )
        return doFindAllValid(query)
    }

    fun find(id: Long): T {
        val query = SimpleSQLiteQuery(
            "select * from $tableName where deleteFlag = 0 and id = ?",
            arrayOf<Any>(id)
        )
        return doFind(query)
    }

    @RawQuery
    protected abstract fun doFindAllValid(query: SupportSQLiteQuery): List<T>

    @RawQuery
    protected abstract fun doFind(query: SupportSQLiteQuery): T

    @RawQuery
    protected abstract fun doDeleteAll(query: SupportSQLiteQuery): Int
}