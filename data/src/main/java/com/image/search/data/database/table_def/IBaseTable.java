package com.image.search.data.database.table_def;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
public interface IBaseTable {

    String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS ";
    String CREATE_UNIQUE_INDEX_IF_NOT_EXISTS = "CREATE UNIQUE INDEX IF NOT EXISTS ";
    String CREATE_INDEX_BASED_ON = " index_based_id ON ";
    String DROP_TABLES = "DROP TABLE IF EXISTS ";
}
