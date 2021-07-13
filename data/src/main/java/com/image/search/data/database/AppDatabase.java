package com.image.search.data.database;

import android.content.Context;

import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.image.search.data.database.entity.ImageModelEntity;


/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
@Database(entities = {ImageModelEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    @VisibleForTesting
    public static final String DB_NAME = "search_image_container.db";

    private static AppDatabase INSTANCE;

    private static final Object sLock = new Object();

    public static AppDatabase getInstance(final Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, DB_NAME)
                        .setJournalMode(JournalMode.WRITE_AHEAD_LOGGING)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        //.addMigrations(MIGRATION_1_2)
                        .build();
            }
        }
        return INSTANCE;
    }
}
