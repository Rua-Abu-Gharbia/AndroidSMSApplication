package com.ruaabugharbia.smsapplication.controller.dataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ruaabugharbia.smsapplication.models.SMSModel;
import com.ruaabugharbia.smsapplication.models.TypeModel;

/**
 * Created by ruaabugharbia on 26-Aug-18.
 */

@Database(entities = {SMSModel.class ,TypeModel.class }, version = 1 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract SMSDao smsDao();

    public abstract TypeDao typeDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .fallbackToDestructiveMigration()  //TODO
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }
}
