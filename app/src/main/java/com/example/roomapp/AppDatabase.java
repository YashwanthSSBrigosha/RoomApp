package com.example.roomapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Names.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NameDao nameDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getINSTANCE(Context context) {

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"myDatabase").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
